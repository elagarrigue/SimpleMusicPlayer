package musicplayer.presentation.bases

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface UiState

interface UiEvent

abstract class BaseViewModel<UiStateType: UiState, UiEventType: UiEvent>(
    application: Application,
    initialUiState: UiStateType
) : AndroidViewModel(application) {

    private val _uiStateLiveData: MutableLiveData<UiStateType> = MutableLiveData()
    val uiStateLiveData: LiveData<UiStateType> get() = _uiStateLiveData
    protected val uiState: UiStateType get() = uiStateLiveData.value ?: throw IllegalStateException("UiState is null")

    private val _uiEventLiveData: MutableLiveData<UiEventType> = MutableLiveData()
    val uiEventLiveData: LiveData<UiEventType> get() = _uiEventLiveData

    init {
        _uiStateLiveData.value = initialUiState
    }

    protected fun updateUiState(newUiState: UiStateType) {
        _uiStateLiveData.value = newUiState
    }

    protected fun postUiEvent(newUiEvent: UiEventType) {
        _uiEventLiveData.value = newUiEvent
    }
}