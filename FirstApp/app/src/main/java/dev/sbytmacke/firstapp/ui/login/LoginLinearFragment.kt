package dev.sbytmacke.firstapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.sbytmacke.firstapp.databinding.FragmentLoginLinearBinding

class LoginLinearFragment : Fragment() {

    private var _binding: FragmentLoginLinearBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentLoginLinearBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textViewLinear
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