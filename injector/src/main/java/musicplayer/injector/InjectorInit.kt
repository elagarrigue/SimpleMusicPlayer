package musicplayer.injector

import android.app.Application
import musicplayer.data.songs.di.dataSongsModule
import musicplayer.presentation.di.presentationModule
import musicplayer.songs.di.domainSongsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun Application.startInjection() {
    startKoin {
        androidContext(this@startInjection)
        modules(presentationModule + domainSongsModule + dataSongsModule)
    }
}