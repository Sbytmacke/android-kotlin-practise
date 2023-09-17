package dev.sbytmacke.firstapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.sbytmacke.firstapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    /* Se utiliza para acceder a las vistas en el archivo de diseño XML asociado a este fragmento,
    con el mismo nombre pero en snake_case */
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        /*
         * Inflar la vista utilizando el archivo de diseño personalizado
         * val view = inflater.inflate(R.layout.my_custom_layout, container, false)
         * return view
        */
        // Se infla/crea el diseño XML asociado, con el mismo nombre pero en snake_case
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Patrón observer, cambios observables en TEXTO
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Devolverá la vista principal del fragmento
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}