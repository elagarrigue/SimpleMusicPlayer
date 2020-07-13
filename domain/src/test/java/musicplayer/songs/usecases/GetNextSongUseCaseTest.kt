package musicplayer.songs.usecases

import io.mockk.every
import io.mockk.mockk
import musicplayer.songs.entities.Song
import musicplayer.songs.repositories.SongsRepository
import org.amshove.kluent.`should be`
import org.junit.Before
import org.junit.Test

class GetNextSongUseCaseTest {

    private val songsRepository: SongsRepository = mockk()

    private val getNextSongUseCase by lazy {
        GetNextSongUseCase(songsRepository)
    }

    private val song1 : Song = mockk {
        every { id } returns "song1"
    }
    private val song2 : Song = mockk{
        every { id } returns "song2"
    }
    private val song3 : Song = mockk{
        every { id } returns "song3"
    }

    @Before
    fun setup() {
        every { songsRepository.getAllSongs() } returns listOf(song1, song2, song3)
    }

    @Test
    fun `given no current song it should get 1st song`() {
        val result = getNextSongUseCase()

        result `should be` song1
    }

    @Test
    fun `given current song 1 it should get song 2`() {
        val result = getNextSongUseCase("song1")

        result `should be` song2
    }

    @Test
    fun `given current song last it should get song 1`() {
        val result = getNextSongUseCase("song3")

        result `should be` song1
    }
}