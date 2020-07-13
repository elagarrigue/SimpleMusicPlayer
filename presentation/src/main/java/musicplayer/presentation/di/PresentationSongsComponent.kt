package musicplayer.presentation.di

import musicplayer.presentation.home.MusicPlayerViewModel
import musicplayer.presentation.utils.MediaPlayerLifecycleObserver
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val presentationModule: Module = module {

    viewModel {
        MusicPlayerViewModel(androidApplication(), get(), get())
    }

    factory { MediaPlayerLifecycleObserver(androidApplication()) }
}