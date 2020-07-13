package musicplayer.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import musicplayer.presentation.R
import musicplayer.presentation.databinding.ActivityMusicPlayerBinding
import musicplayer.presentation.utils.MediaPlayerLifecycleObserver
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MusicPlayerActivity : AppCompatActivity() {

    private val viewModel: MusicPlayerViewModel by viewModel()

    private lateinit var binding: ActivityMusicPlayerBinding

    private val mediaPlayerLifecycleObserver: MediaPlayerLifecycleObserver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_music_player)
        binding.lifecycleOwner = this
        binding.uiState = viewModel.uiStateLiveData

        lifecycle.addObserver(mediaPlayerLifecycleObserver)

        viewModel.init()
        viewModel.uiEventLiveData.observe(this, Observer {
            mediaPlayerLifecycleObserver.loadSong(it.playResource)
            viewModel.onPlayAction(mediaPlayerLifecycleObserver.isPlaying())
        })

        initUiListeners()
    }

    private fun initUiListeners() {
        binding.buttonPlay.setOnClickListener {
            mediaPlayerLifecycleObserver.onPlay()
            viewModel.onPlayAction(mediaPlayerLifecycleObserver.isPlaying())
        }
        binding.buttonNext.setOnClickListener { viewModel.onNextAction() }
        binding.buttonPrevious.setOnClickListener { viewModel.onPreviousAction() }
    }
}