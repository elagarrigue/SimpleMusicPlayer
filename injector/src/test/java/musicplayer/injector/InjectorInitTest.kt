package musicplayer.injector

import android.app.Application
import io.mockk.mockk
import musicplayer.data.songs.di.dataSongsModule
import musicplayer.presentation.di.presentationModule
import musicplayer.songs.di.domainSongsModule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class InjectorInitTest : KoinTest {


    @Test
    fun `check modules`() {
        startKoin {
            androidContext(mockk<Application>(relaxed = true))
            loadKoinModules(presentationModule + domainSongsModule + dataSongsModule)
        }.checkModules()
    }
}