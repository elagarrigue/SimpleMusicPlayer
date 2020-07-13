package musicplayer.songs.di

import musicplayer.songs.usecases.GetNextSongUseCase
import musicplayer.songs.usecases.GetPreviousSongUseCase
import org.koin.dsl.module

val domainSongsModule = module {

    single { GetNextSongUseCase(get()) }
    single { GetPreviousSongUseCase(get()) }
}