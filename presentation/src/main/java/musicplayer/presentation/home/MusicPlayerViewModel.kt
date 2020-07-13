package musicplayer.presentation.home

import android.app.Application
import androidx.core.content.ContextCompat
import musicplayer.presentation.R
import musicplayer.presentation.bases.BaseViewModel
import musicplayer.songs.usecases.GetNextSongUseCase
import musicplayer.songs.usecases.GetPreviousSongUseCase
import musicplayer.songs.entities.Song
import musicplayer.presentation.utils.resourceNameToRawRes

class MusicPlayerViewModel(
    private val appContext: Application,
    private val getNextSongUseCase: GetNextSongUseCase,
    private val getPreviousSongUseCase: GetPreviousSongUseCase
) : BaseViewModel<MusicPlayerUiState, MusicPlayerUiEvent>(appContext, MusicPlayerUiState()) {

    fun init() {
        updateSongInfo(getNextSongUseCase())
    }

    fun onPlayAction(isPlaying: Boolean) {
        updateUiState(
            uiState.copy(
                playIcon =
                ContextCompat.getDrawable(
                    appContext,
                    if (isPlaying) R.drawable.ic_pause_circle_outline_24px
                    else R.drawable.ic_play_circle_outline_24px
                )
            )
        )
    }

    fun onNextAction() {
        updateSongInfo(getNextSongUseCase(uiState.songId))
    }

    fun onPreviousAction() {
        updateSongInfo(getPreviousSongUseCase(uiState.songId))
    }

    private fun updateSongInfo(song: Song) {
        updateUiState(
            uiState.copy(
                songId = song.id,
                songTitle = "${song.songName} - ${song.artistName}"
            )
        )

        appContext.resourceNameToRawRes(song.resourceName)?.let {
            postUiEvent(MusicPlayerUiEvent(it))
        }
    }
}