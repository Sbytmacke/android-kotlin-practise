package dev.sbytmacke.firstapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        /* Ejercicio */
        // 3 elementos para meter datos
        // 1 Checkbox
        // 1 RadioButton
        // No utilizar editText, utilizar simpleLayout?

        /*      binding.buttonLogin.setOnClickListener {
                  login()
              }*/

        /*     binding.buttonRegister.setOnClickListener {
                 register()
             }*/

        return binding.root
    }

    /*
        fun register() {
            val intent = Intent(context, BoobaTypingFragment::class.java)
            val textName = binding.editTextName.text.toString()
            val userEmail = binding.editTextEmailAddress.text.toString()
            val userPass = binding.editTextPassword.text.toString()
            val textPhone = binding.editTextPhone.text.toString()

            val initString =
                "Bienvenido $textName, email: $userEmail, password: $userPass, phone: $textPhone"
            Toast.makeText(context, initString, Toast.LENGTH_SHORT).show()
        }
    */

    /*    fun login() {
            // Texto almacenado en el email en nuestro layout
            val userEmail = binding.editTextEmailAddress.text.toString()
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
            }
        }*/


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}