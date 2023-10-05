package dev.sbytmacke.firstapp.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.sbytmacke.firstapp.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null

    private val binding get() = _binding!!

    // Variables para el cálculo
    private var currentInput = StringBuilder()
    private var lastResult: Double? = null
    private var currentOperator: Char? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        // Manejar los clicks en los botones numéricos
        val numberButtons = listOf(
            binding.number0, binding.number1, binding.number2, binding.number3, binding.number4,
            binding.number5, binding.number6, binding.number7, binding.number8, binding.number9
        )
        for (button in numberButtons) {
            button.setOnClickListener {
                appendToInput(button.text.toString())
            }
        }

        val operatorButtons = listOf(
            binding.buttonPlus, binding.buttonMinus, binding.buttonDivision, binding.buttonMultiply
        )
        for (button in operatorButtons) {
            button.setOnClickListener {
                handleOperatorClick(button.text.toString()[0])
            }
        }

        // Solamente dejamos un decimal por número
        binding.buttonDot.setOnClickListener {
            if (!currentInput.contains('.')) {
                appendToInput(".")
            }
        }

        binding.buttonDelete.setOnClickListener {
            clearInput()
        }

        binding.buttonCalculate.setOnClickListener {
            calculateResult()
        }
        // Hacer calculadora de descuentos, y hacer función que detecte el punto de los decimales

        return binding.root
    }

    private fun appendToInput(text: String) {
        currentInput.append(text)
        binding.resultDynamic.text = currentInput.toString()
    }

    private fun clearInput() {
        currentInput.clear()
        binding.resultDynamic.text = ""
        lastResult = null
        currentOperator = null
    }

    private fun handleOperatorClick(operator: Char) {
        if (currentInput.isNotEmpty()) {
            if (lastResult != null && currentOperator != null) {
                calculateResult()
            } else {
                lastResult = currentInput.toString().toDouble()
                currentInput.clear()
                currentOperator = operator
            }
        }
    }

    private fun calculateResult() {
        if (lastResult != null && currentOperator != null && currentInput.isNotEmpty()) {
            val currentNumber = currentInput.toString().toDouble()
            when (currentOperator) {
                '+' -> lastResult = lastResult!! + currentNumber
                '-' -> lastResult = lastResult!! - currentNumber
                '*' -> lastResult = lastResult!! * currentNumber
                '/' -> lastResult = lastResult!! / currentNumber
            }
            currentInput.clear()
            binding.resultDynamic.text = lastResult.toString()

            // Limpiamos el operador, para obligar a elegirlo de nuevo
            currentOperator = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

