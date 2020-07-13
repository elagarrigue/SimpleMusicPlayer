package musicplayer.presentation.home

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import musicplayer.presentation.R
import musicplayer.presentation.utils.resourceNameToRawRes
import musicplayer.songs.entities.Song
import musicplayer.songs.usecases.GetNextSongUseCase
import musicplayer.songs.usecases.GetPreviousSongUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MusicPlayerViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val stateObserver: Observer<MusicPlayerUiState> = mockk(relaxUnitFun = true)
    private val uiEventObserver: Observer<MusicPlayerUiEvent> = mockk(relaxUnitFun = true)

    private val context: Application = mockk()
    private val getNextSongUseCase: GetNextSongUseCase = mockk()
    private val getPreviousSongUseCase: GetPreviousSongUseCase = mockk()

    private val viewModel by lazy {
        MusicPlayerViewModel(context, getNextSongUseCase, getPreviousSongUseCase)
    }

    private val playIcon: Drawable = mockk()
    private val pauseIcon: Drawable = mockk()

    @Before
    fun before() {
        viewModel.uiStateLiveData.observeForever(stateObserver)
        viewModel.uiEventLiveData.observeForever(uiEventObserver)

        mockkStatic("musicplayer.presentation.utils.ContextExtensionsKt")
        every { context.resourceNameToRawRes("res01") } returns 1

        mockkStatic(ContextCompat::class)
        every { ContextCompat.getDrawable(context, R.drawable.ic_pause_circle_outline_24px) } returns pauseIcon
        every { ContextCompat.getDrawable(context, R.drawable.ic_play_circle_outline_24px) } returns playIcon

    }

    @Test
    fun `on init it should set state with first song`() {
        val song = Song(
            "song1",
            "The Song",
            "Artist",
            "res01"
        )
        every { getNextSongUseCase() } returns song

        viewModel.init()

        verify {
            stateObserver.onChanged(
                MusicPlayerUiState("song1", "The Song - Artist")
            )
        }

        verify {
            uiEventObserver.onChanged(
                MusicPlayerUiEvent(1)
            )
        }
    }

    @Test
    fun `on play it should update pause icon state`() {
        viewModel.onPlayAction(true)

        verify {
            stateObserver.onChanged(
                MusicPlayerUiState("", "", pauseIcon)
            )
        }
    }

    @Test
    fun `on pause it should update play icon state`() {
        viewModel.onPlayAction(false)

        verify {
            stateObserver.onChanged(
                MusicPlayerUiState("", "", playIcon)
            )
        }
    }

    @Test
    fun `on next song it should set state with next song`() {
        val song = Song(
            "song1",
            "The Song",
            "Artist",
            "res01"
        )
        every { getNextSongUseCase("") } returns song

        viewModel.onNextAction()

        verify {
            stateObserver.onChanged(
                MusicPlayerUiState("song1", "The Song - Artist")
            )
        }

        verify {
            uiEventObserver.onChanged(
                MusicPlayerUiEvent(1)
            )
        }
    }

    @Test
    fun `on previous song it should set state with next song`() {
        val song = Song(
            "song1",
            "The Song",
            "Artist",
            "res01"
        )
        every { getPreviousSongUseCase("") } returns song

        viewModel.onPreviousAction()

        verify {
            stateObserver.onChanged(
                MusicPlayerUiState("song1", "The Song - Artist")
            )
        }

        verify {
            uiEventObserver.onChanged(
                MusicPlayerUiEvent(1)
            )
        }
    }
}