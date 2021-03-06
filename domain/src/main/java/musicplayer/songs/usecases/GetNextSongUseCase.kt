package musicplayer.songs.usecases

import musicplayer.songs.entities.Song
import musicplayer.songs.repositories.SongsRepository

class GetNextSongUseCase(
    private val songsRepository: SongsRepository
) {

    operator fun invoke(currentSongId: String? = null): Song {
        return with(songsRepository.getAllSongs()) {
            val indexOfCurrent = indexOfFirst { it.id == currentSongId }
            getOrNull(indexOfCurrent + 1) ?: first()
        }
    }
}