package musicplayer.songs.usecases

import io.mockk.every
import io.mockk.mockk
import musicplayer.songs.entities.Song
import musicplayer.songs.repositories.SongsRepository
import org.amshove.kluent.`should be`
import org.junit.Before
import org.junit.Test

class GetPreviousSongUseCaseTest {

    private val songsRepository: SongsRepository = mockk()

    private val getPreviousSongUseCase by lazy {
        GetPreviousSongUseCase(songsRepository)
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
    fun `given no current song it should get last song`() {
        val result = getPreviousSongUseCase()

        result `should be` song3
    }

    @Test
    fun `given current song 2 it should get song 1`() {
        val result = getPreviousSongUseCase("song2")

        result `should be` song1
    }

    @Test
    fun `given current song first it should get song 3`() {
        val result = getPreviousSongUseCase("song1")

        result `should be` song3
    }
}