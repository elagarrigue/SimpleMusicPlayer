package musicplayer.data.songs.di

import musicplayer.data.songs.SongsRepositoryImpl
import musicplayer.songs.repositories.SongsRepository
import org.koin.dsl.module

val dataSongsModule = module {

    single<SongsRepository> { SongsRepositoryImpl() }
}