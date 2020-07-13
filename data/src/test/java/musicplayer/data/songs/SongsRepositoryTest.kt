package musicplayer.data.songs

import musicplayer.songs.entities.Song
import org.amshove.kluent.`should be equal to`
import org.junit.Test

class SongsRepositoryTest {

    private val songsRepository by lazy {
        SongsRepositoryImpl()
    }

    @Test
    fun `should get all songs`() {
        val expected = listOf(
            Song(
                "total_breakdown",
                "Total Breakdown",
                "Brad Sucks",
                "brad_sucks_total_breakdown"
            ),
            Song(
                "sorry",
                "Sorry",
                "Comfort Fit",
                "comfort_fit_sorry"
            ),
            Song(
                "swan_song",
                "Swan Song",
                "Paper Navy",
                "paper_navy_swan_song"
            )
        )

        val result = songsRepository.getAllSongs()

        result `should be equal to` expected
    }
}