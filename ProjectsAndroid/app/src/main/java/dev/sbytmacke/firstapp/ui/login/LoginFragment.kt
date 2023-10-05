package dev.sbytmacke.firstapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.sbytmacke.firstapp.R
import dev.sbytmacke.firstapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        // BUTTON
        binding.buttonRegister.setOnClickListener {
            login()
        }

        // LINK
        binding.textViewLink.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        return binding.root
    }

    fun login() {
        // Texto almacenado en el email en nuestro layout
        /*            val userEmail = binding.editTextEmailAddress.text.toString()
                    val userPass = binding.editTextPassword.text.toString()

                    if (!userEmail.contains("@")) {
                        Toast.makeText(context, "Email incorrecto", Toast.LENGTH_SHORT).show()
                        return
                    }

                    if (userEmail.isEmpty() || userPass.isEmpty()) {
                        Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                        return
                    } else {
                        Toast.makeText(context, "Bienvenido $userEmail", Toast.LENGTH_SHORT).show()
                    }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}