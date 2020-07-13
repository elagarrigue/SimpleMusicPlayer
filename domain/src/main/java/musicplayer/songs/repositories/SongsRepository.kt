package musicplayer.songs.repositories

import musicplayer.songs.entities.Song

interface SongsRepository {

    fun getAllSongs(): List<Song>
}