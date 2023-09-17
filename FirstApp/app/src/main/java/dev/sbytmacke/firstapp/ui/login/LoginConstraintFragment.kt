package dev.sbytmacke.firstapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.sbytmacke.firstapp.databinding.FragmentLoginConstraintBinding

class LoginConstraintFragment : Fragment() {

    private var _binding: FragmentLoginConstraintBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentLoginConstraintBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textViewLoginConstraint
        loginViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}