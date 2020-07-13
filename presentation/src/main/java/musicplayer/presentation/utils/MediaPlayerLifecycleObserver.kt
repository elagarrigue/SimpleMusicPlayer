package musicplayer.presentation.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MediaPlayerLifecycleObserver(
    private val context: Context
) : DefaultLifecycleObserver {

    private var mediaPlayer: MediaPlayer? = null

    fun onPlay() {
        mediaPlayer?.let {
            if (it.isPlaying) mediaPlayer?.pause()
            else mediaPlayer?.start()
        }
    }

    fun isPlaying() = mediaPlayer?.isPlaying == true

    fun loadSong(@RawRes songId: Int) {
        mediaPlayer?.release()
        mediaPlayer = null
        mediaPlayer = MediaPlayer.create(context, songId)
        mediaPlayer?.start()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        mediaPlayer?.release()
        mediaPlayer = null
    }

}