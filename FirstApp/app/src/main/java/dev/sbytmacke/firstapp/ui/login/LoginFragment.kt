package dev.sbytmacke.firstapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        binding.buttonRegister.setOnClickListener {
            register()
        }

        // RADIO BUTTON
        val radioButton = binding.radioGroupGender
        radioButton.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButtonMale -> {
                    Toast.makeText(context, "Ha seleccionado hombre", Toast.LENGTH_SHORT).show()
                }

                R.id.radioButtonFemale -> {
                    Toast.makeText(context, "Ha seleccionado mujer", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // SPINNER
        val daysSpinner = resources.getStringArray(R.array.days)
        val monthSpinner = resources.getStringArray(R.array.months)
        val yearSpinner = resources.getStringArray(R.array.years)

        // Creamos el ADAPTADOR, es DONDE y COMO se despliegan los datos
        val daySpinnerAdapter = object : ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            daysSpinner
        ) {
            override fun isEnabled(position: Int): Boolean {
                // Excluye la opción "Day" para que no sea seleccionable
                return position != 0
            }
        }

        val monthSpinnerAdapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            monthSpinner
        )

        val yearSpinnerAdapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            yearSpinner
        )

        // Asignamos el ADAPTADOR al LISTVIEW
        binding.daySpinner.adapter = daySpinnerAdapter
        binding.monthSpinner.adapter = monthSpinnerAdapter
        binding.yearSpinner.adapter = yearSpinnerAdapter

        // CHECKBOX
        val checkBoxAgreeTerm = binding.checkBoxAgree
        val buttonRegister = binding.buttonRegister
        buttonRegister.setOnClickListener {
            if (checkBoxAgreeTerm.isChecked) {
                Toast.makeText(context, "Ha aceptado los términos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "No ha aceptado los términos", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
    
    fun register() {
        /*  val intent = Intent(context, BoobaTypingFragment::class.java)
          val textName = binding.editTextName.text.toString()
          val userEmail = binding.editTextEmailAddress.text.toString()
          val userPass = binding.editTextPassword.text.toString()
          val textPhone = binding.editTextPhone.text.toString()

          val initString =
              "Bienvenido $textName, email: $userEmail, password: $userPass, phone: $textPhone"
          Toast.makeText(context, initString, Toast.LENGTH_SHORT).show()*/
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