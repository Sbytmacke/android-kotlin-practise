package dev.sbytmacke.firstapp

import android.content.ContentValues.TAG
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dev.sbytmacke.firstapp.databinding.ActivityFirstBinding
import dev.sbytmacke.firstapp.ui.practiseActivities.EDAD_EXTRA

// En milisegundos; 10k = 10 segundos
private var positon: Int = 10000

// Cada actividad tiene un layout, es como otra pantalla, tiene que estar declarada en el AndroidManifest.xml
// Si la generamos con IntellIJ, se genera automáticamente
class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        Log.i(TAG, "onCreateReproductor")

        // Reproducir audio
        mediaPlayer = MediaPlayer.create(this, R.raw.lofi)
        mediaPlayer?.seekTo(positon)
        mediaPlayer?.start()

        // Introducimos la clave de otra actividad
        val edad = intent.getStringExtra(EDAD_EXTRA)
        binding.age.text = edad
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
        positon = mediaPlayer?.currentPosition!!
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.seekTo(positon)
    }

    /*    override fun onStop() {
            super.onStop()
            mediaPlayer?.stop()
        }*/

}