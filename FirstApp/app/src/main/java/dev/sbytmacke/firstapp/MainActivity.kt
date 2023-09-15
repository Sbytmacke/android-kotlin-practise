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

    // Instancia para enlazar una interfaz XML con el resto de componentes
    /* Y realizar la jerarquía */
    private lateinit var binding: ActivityMainBinding

    // Instancia de la barra de navegación, para configurar su comportamiento
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar se refiere a establecer como raíz el XML llamado activity_main.xml
        /* Es posible, debido a que en el build.gradle tenemos: viewBinding { enabled = true } */
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Asignamos que la interfaz raíz XML sea: activity_main.xml
        setContentView(binding.root)

        // Colocamos barra de acción superior, proporcionado por la librería Android
        /* En caso de querer utilizar otra ActionBar, debemos en el XML cambiarlo */
        setSupportActionBar(binding.appBarMain.toolbar)

        // Contenedor lateral, desliza la navegación (apertura y cierre)
        val drawerLayout: DrawerLayout = binding.drawerLayout

        // Mediante el controlador realizamos la navegación gracias a los ID´s
        /* Asignamos aquí el fragmento inicial/home */
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Inicializamos la configuración de la barra de navegación con sus destinos y su contenedor
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_login,
                R.id.nav_login_linear,
                R.id.nav_home
            ), drawerLayout
        )

        // Componente lateral de la navegación, donde se acoplan todos los elementos junto al controlador
        val navView: NavigationView = binding.navView
        navView.setupWithNavController(navController)

        // Cargamos la configuración junto al controlador
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}