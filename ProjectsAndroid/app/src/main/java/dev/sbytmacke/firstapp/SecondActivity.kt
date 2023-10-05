package dev.sbytmacke.firstapp

import android.content.ContentValues
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dev.sbytmacke.firstapp.databinding.ActivitySecondBinding

// En milisegundos; 10k = 10 segundos
private var positon: Int = 10000

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.i(ContentValues.TAG, "onCreateVideo")
        // Video
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.video)
        binding.videoView.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
        positon = binding.videoView.currentPosition
    }

    override fun onResume() {
        super.onResume()
        binding.videoView.seekTo(positon)
    }

    // Si nos salimos la actividad se destruye y no se guarda la posición
    /* override fun onStop() {
         super.onStop()
         mediaPlayer?.stop()
     }*/
}