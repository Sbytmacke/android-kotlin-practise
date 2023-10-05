package dev.sbytmacke.firstapp.ui.boobaTyping

import android.content.Context
import android.media.MediaPlayer

class MusicPlayerManager {

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 24000

    fun startMusic(context: Context, rawMedia: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, rawMedia)
            mediaPlayer?.isLooping = true
            mediaPlayer?.seekTo(position)
            mediaPlayer?.start()
        }
    }

    fun pauseMusic() {
        mediaPlayer?.pause()
        position = mediaPlayer?.currentPosition ?: 0
    }

    fun resumeMusic() {
        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
