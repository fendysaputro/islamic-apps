package id.phephen.al_islamic_apps.helper

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer

/**
 * Created by Phephen on 31/05/2022.
 */
class Helper(val context: Context) {

    val mediaPlayer = MediaPlayer()

    fun playAudio(url: String): Boolean {
        mediaPlayer.apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare()
            start()
        }
        return checkMedia()
    }

    fun stopAudio(): Boolean {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
        return false
    }

    fun checkMedia(): Boolean {
        return mediaPlayer.isPlaying
    }

}