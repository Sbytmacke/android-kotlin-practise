package dev.sbytmacke.firstapp

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import dev.sbytmacke.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Instancia para enlazar una interfaz XML con el resto de componentes, jerarquizada por nodos
    /* Realiza jerarquía por nodos entre distintas vistas y elementos, empezando por root */
    private lateinit var binding: ActivityMainBinding

    // Instancia de la barra de navegación, para configurar su comportamiento
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar se refiere a crear como raíz el XML llamado activity_main.xml
        /* Es posible, debido a que en el build.gradle tenemos: viewBinding { enabled = true } */
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Es posible prescindir del "binding" y "viewBinding", pero es más óptimo con él, ya que garantiza referencias en tiempo de compilación
        /*val inflater = layoutInflater
        val rootView = inflater.inflate(R.layout.nuevo_layout, null)*/

        // Asignamos que la interfaz raíz XML sea: activity_main.xml
        setContentView(binding.root)

        // Colocamos barra de acción superior, proporcionado por la librería Android
        /* En caso de querer utilizar otra ActionBar, debemos en el XML cambiarlo */
        // setSupportActionBar(binding.appBarMain.toolbar) // Convencional de android + òptimo
        setSupportActionBar(findViewById(R.id.toolbar))

        // Contenedor lateral, desliza la navegación (apertura y cierre)
        //val conventionalDrawerLayout: DrawerLayout = binding.drawerLayout // Convencional de android + òptimo
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_main)

        // Mediante el controlador realizamos la navegación gracias a los ID´s
        /* Asignamos aquí el fragmento inicial/home */
        val navController = findNavController(R.id.fragment_nav_controller)

        // Inicializamos la configuración de la barra de navegación con sus destinos y su contenedor
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_login,
                R.id.nav_booba_typing,
                R.id.nav_notes,
                R.id.nav_calculator,
                R.id.nav_activities
            ), drawerLayout
        )

        // Componente lateral de la navegación, donde se acoplan todos los elementos junto al controlador
        // val navView: NavigationView = binding.navView // Convencional de android + òptimo
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setupWithNavController(navController)

        // Cargamos la configuración junto al controlador
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // Infla = Crea el menú al instanciar MainActivity, utilizando la vista XML: menu/main.xml
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.items_action_bar, menu)
        return true
    }

    // Acción de navegación hacia atrás
    override fun onSupportNavigateUp(): Boolean {
        // Buscamos al controlador en el XML
        val navController = findNavController(R.id.fragment_nav_controller)
        // Devuelve true si la navegación se realiza hacia atrás
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}