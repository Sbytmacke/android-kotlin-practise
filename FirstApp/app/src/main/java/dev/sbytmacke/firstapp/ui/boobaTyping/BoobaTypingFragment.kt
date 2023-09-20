package dev.sbytmacke.firstapp.ui.boobaTyping

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.sbytmacke.firstapp.databinding.FragmentBoobaTypingBinding

class BoobaTypingFragment : Fragment() {

    private var _binding: FragmentBoobaTypingBinding? = null
    private val binding get() = _binding!!
    private val handler = Handler(Looper.getMainLooper())
    private var currentRandomLetter = randomLetter()
    private var timeToChangeLetter = 2000L
    private var currentStreak = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBoobaTypingBinding.inflate(inflater, container, false)

        val boobaTypingViewModel = ViewModelProvider(this)[BoobaTypingViewModel::class.java]

        binding.countStreak.text = currentStreak.toString()
        binding.countRecord.text = 0.toString()

        startPeriodicUpdate(boobaTypingViewModel)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }

    private fun startPeriodicUpdate(boobaTypingViewModel: BoobaTypingViewModel) {
        handler.postDelayed(object : Runnable {
            override fun run() {
                /*
                                val file = "high_score.txt"

                                boobaTypingViewModel.escribirEnAlmacenamientoInterno(
                                    requireContext(),
                                    file,
                                    currentStreak.toString()
                                )

                                val highScore = boobaTypingViewModel.leerDesdeAlmacenamientoInterno(
                                    requireContext(),
                                    file
                                ).toInt()

                                if (currentStreak > highScore) {
                                    binding.countRecord.text = currentStreak.toString()
                                    boobaTypingViewModel.escribirEnAlmacenamientoInterno(
                                        requireContext(),
                                        "high_score.txt",
                                        currentStreak.toString()
                                    )
                                }
                */

                binding.countRecord.text = 0.toString()

                val text = binding.editTextBooba.text.toString()
                if (text.uppercase() == currentRandomLetter.toString()) {
                    currentStreak++
                    binding.countStreak.text = currentStreak.toString()
                } else {
                    // Si la entrada no coincide y no está vacía, restablece el contador a 0
                    currentStreak = 0
                    binding.countStreak.text = currentStreak.toString()
                }

                binding.editTextBooba.text.clear() // Limpiamos el edit

                currentRandomLetter = randomLetter()
                binding.textRandomBoobaTyping.text = currentRandomLetter.toString()

                // Volver a programar la actualización después de 2 segundos
                handler.postDelayed(this, timeToChangeLetter)
            }
        }, timeToChangeLetter)
    }

    fun randomLetter(): Char {
        val letrasPermitidas = "QERTASDFG"
        val random = java.util.Random()
        val indice = random.nextInt(letrasPermitidas.length)
        return letrasPermitidas[indice]
    }
}