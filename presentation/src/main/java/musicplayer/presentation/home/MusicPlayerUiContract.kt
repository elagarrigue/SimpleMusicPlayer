package musicplayer.presentation.home

import android.graphics.drawable.Drawable
import androidx.annotation.IntegerRes
import androidx.annotation.RawRes
import musicplayer.presentation.bases.UiEvent
import musicplayer.presentation.bases.UiState

data class MusicPlayerUiState(
    val songId: String = "",
    val songTitle: String = "",
    val playIcon: Drawable? = null
) : UiState

data class MusicPlayerUiEvent(
    @RawRes
    val playResource : Int
): UiEvent