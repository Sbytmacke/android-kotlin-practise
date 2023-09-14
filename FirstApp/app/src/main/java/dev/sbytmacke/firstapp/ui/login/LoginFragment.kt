package dev.sbytmacke.firstapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.sbytmacke.firstapp.R
import dev.sbytmacke.firstapp.databinding.FragmentHomeBinding

class LoginFragment : Fragment() {

    // TODO: Como cambio el fragment de HomeBinding a  LoginFragment??
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)

        // El inflater es el layout?
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_main, container, false)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        loginViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Retornamos root o view
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}