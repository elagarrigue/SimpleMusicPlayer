package musicplayer.data.songs

import musicplayer.songs.entities.Song
import musicplayer.songs.repositories.SongsRepository

internal class SongsRepositoryImpl : SongsRepository {

    override fun getAllSongs(): List<Song> =
        listOf(
            // https://freemusicarchive.org/music/Brad_Sucks/Out_Of_It/07_-_Brad_Sucks_-_Total_Breakdown
            Song(
                "total_breakdown",
                "Total Breakdown",
                "Brad Sucks",
                "brad_sucks_total_breakdown"
            ),
            // https://freemusicarchive.org/music/Comfort_Fit/Forget_And_Remember/03_Sorry
            Song(
                "sorry",
                "Sorry",
                "Comfort Fit",
                "comfort_fit_sorry"
            ),
            // https://freemusicarchive.org/music/Paper_Navy/All_Grown_Up/08_Swan_Song
            Song(
                "swan_song",
                "Swan Song",
                "Paper Navy",
                "paper_navy_swan_song"
            )
        )
}