package dev.sbytmacke.firstapp.ui.boobaTyping

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.sbytmacke.firstapp.R
import dev.sbytmacke.firstapp.databinding.FragmentBoobaTypingBinding

class BoobaTypingFragment : Fragment() {

    private var _binding: FragmentBoobaTypingBinding? = null
    private val binding get() = _binding!!

    private var currentStreak = 0
    private var currentTopRecord = 0
    private lateinit var randomText: String
    private val numberButton = 7
    private var currentIndex = 0

    // Timer
    private var countdownTimer: CountDownTimer? = null
    private var timeToResetLoop = 4000L // Tiempo máximo que dura el total de ciclos
    private var timeInterval = 1000L // Cada segundo es un ciclo

    // Music
    private var musicPlayerManager: MusicPlayerManager = MusicPlayerManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        musicPlayerManager.startMusic(requireContext(), R.raw.vykas)
    }

    override fun onPause() {
        super.onPause()
        musicPlayerManager.pauseMusic()
    }

    override fun onResume() {
        super.onResume()
        musicPlayerManager.resumeMusic()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBoobaTypingBinding.inflate(inflater, container, false)

        // Para después hacer cositas con los ficheros
        //val boobaTypingViewModel = ViewModelProvider(this)[BoobaTypingViewModel::class.java]

        // Configura el temporizador inicial
        startCountdownTimer()

        // Generar el texto aleatorio
        randomText = generateRandomText(numberButton)
        updateTextViews()

        // Comprobar el botón pulsado por el usuario
        binding.buttonQ.setOnClickListener {
            checkLetter('Q')
            updateTextViews()
        }
        binding.buttonW.setOnClickListener {
            checkLetter('W')
            updateTextViews()
        }
        binding.buttonE.setOnClickListener {
            checkLetter('E')
            updateTextViews()
        }
        binding.buttonA.setOnClickListener {
            checkLetter('A')
            updateTextViews()
        }
        binding.buttonS.setOnClickListener {
            checkLetter('S')
            updateTextViews()
        }
        binding.buttonD.setOnClickListener {
            checkLetter('D')
            updateTextViews()
        }
        return binding.root
    }

    // Inicia el temporizador
    private fun startCountdownTimer() {
        countdownTimer = object : CountDownTimer(timeToResetLoop, timeInterval) {
            override fun onTick(millisUntilFinished: Long) {
                // Este método se llama cada segundo durante la cuenta atrás
            }

            override fun onFinish() {
                // Este método se llama cuando el temporizador se agota
                handleTimerFinish()
            }
        }

        // Inicia el temporizador
        countdownTimer?.start()
    }

    // Función para manejar la finalización del temporizador
    private fun handleTimerFinish() {
        // Aquí debemos generar nuevas letras y contar como un fallo
        currentStreak = 0
        currentIndex = 0
        randomText = generateRandomText(numberButton)
        binding.countStreak.text = "0"
        updateTextViews()

        // Reiniciamos el temporizador para el siguiente ciclo
        startCountdownTimer()
    }

    private fun checkLetter(letter: Char) {
        // Acertamos letra
        if (currentIndex + 1 <= randomText.length && randomText[currentIndex] == letter) {
            // Si es la última letra, aumentamos la racha y generamos un nuevo texto
            if (currentIndex + 1 == randomText.length) {
                currentStreak++
                currentIndex = 0
                randomText = generateRandomText(numberButton)
                binding.countStreak.text = currentStreak.toString()
                if (currentStreak > currentTopRecord) {
                    currentTopRecord = currentStreak
                    binding.countRecord.text = currentStreak.toString()
                }

                // Cancelamos el temporizador existente y reiníciamos
                countdownTimer?.cancel()
                startCountdownTimer()
            } else {
                // Si no es la última letra, avanzamos
                currentIndex++
                updateTextViews()
            }
        } else {
            // Si falla una letra reseteamos
            currentStreak = 0
            currentIndex = 0
            randomText = generateRandomText(numberButton)
            binding.countStreak.text = "0"

            // Cancelamos el temporizador existente y reiníciamos
            countdownTimer?.cancel()
            startCountdownTimer()
        }
    }

    private fun updateTextViews() {
        // Recorremos todos los textView para no comprobar cada uno por separado una y otra vez
        for (i in 0 until numberButton + 1) {
            // switch expression
            val textView: TextView? = when (i) {
                0 -> binding.textRandomTyping1
                1 -> binding.textRandomTyping2
                2 -> binding.textRandomTyping3
                3 -> binding.textRandomTyping4
                4 -> binding.textRandomTyping5
                5 -> binding.textRandomTyping6
                6 -> binding.textRandomTyping7
                else -> null
            }

            if (textView != null) {
                if (i < currentIndex) {
                    // Cambiar el color del texto a verde si acertamos
                    textView.setTextColor(resources.getColor(android.R.color.holo_green_light))
                } else {
                    // El resto de letras las dejamos en el mismo color
                    textView.setTextColor(android.graphics.Color.parseColor("#e02496"))
                }
            }
        }
    }

    private fun generateRandomText(sizeString: Int): String {
        val arrayRandomLetter = Array(sizeString) { "" }
        for (i in 0..sizeString - 1) {
            arrayRandomLetter[i] = randomLetter().toString()
        }
        binding.textRandomTyping1.text = arrayRandomLetter[0]
        binding.textRandomTyping2.text = arrayRandomLetter[1]
        binding.textRandomTyping3.text = arrayRandomLetter[2]
        binding.textRandomTyping4.text = arrayRandomLetter[3]
        binding.textRandomTyping5.text = arrayRandomLetter[4]
        binding.textRandomTyping6.text = arrayRandomLetter[5]
        binding.textRandomTyping7.text = arrayRandomLetter[6]

        return arrayRandomLetter.joinToString("") { it }
    }

    private fun randomLetter(): Char {
        val letrasPermitidas = "QWEASD"
        val random = java.util.Random()
        val indice = random.nextInt(letrasPermitidas.length)
        return letrasPermitidas[indice]
    }

    // En onDestroyView, aseguramos cancelar el temporizador para evitar fugas de memoria
    override fun onDestroyView() {
        super.onDestroyView()
        // musicPlayerManager.release() // Esto si queremos liberar totalmente de la memoria
        countdownTimer?.cancel()
        _binding = null
    }
}