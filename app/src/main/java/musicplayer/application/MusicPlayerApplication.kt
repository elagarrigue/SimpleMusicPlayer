package musicplayer.application

import android.app.Application
import musicplayer.injector.startInjection

class MusicPlayerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startInjection()
    }
}