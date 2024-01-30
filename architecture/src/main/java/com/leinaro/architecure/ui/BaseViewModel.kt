package com.leinaro.architecure.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leinaro.architecure.ui.UiState.Loading
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

abstract class BaseViewModel<T : Any> : ViewModel() {

    // 8, 9,  24,
    protected open fun getprice() =5
    protected val _state = MutableStateFlow<UiState<T>>(Loading)
    val state: StateFlow<UiState<T>> = _state

    protected val _sideEffect = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffect: Flow<SideEffect>
        get() = _sideEffect.receiveAsFlow()

    /*private val viewData = MutableStateFlow<ViewDataState<T>?>(null)
    private val errorViewData = MutableStateFlow<ViewDataState<ErrorViewData>?>(null)
    private val eventChannel = Channel<ViewEventState<T>>(Channel.BUFFERED)
    private val eventsFlow = eventChannel.receiveAsFlow()

    fun getViewData(): StateFlow<ViewDataState<T>?> = viewData
    fun getErrorViewData(): StateFlow<ViewDataState<ErrorViewData>?> = errorViewData
    fun getEventsFlow(): Flow<ViewEventState<T>?> = eventsFlow

    fun setValue(value: T, handler: ViewHandler<out T, out BaseViewModel<T>>) {
        viewData.value = ViewDataState(value, handler)
    }

    fun setErrorValue(
        value: ErrorViewData,
        handler: ViewHandler<ErrorViewData, BaseViewModel<ErrorViewData>>,
    ) {
        errorViewData.value = ViewDataState(value, handler)
    }

    open fun showError(throwable: Throwable) {
        //setErrorValue(DefaultError(throwable), ShowGeneralErrorViewHandler)
    }

    suspend fun sendEvent(
        viewEvent: ViewEvent,
        handler: ViewHandler<out ViewEvent, out BaseViewModel<T>>
    ) {
        eventChannel.send(ViewEventState(viewEvent, handler))
    }*/

}

sealed class ViewEvent {
    object NavigateToEmptyFragment : ViewEvent()
    data class ShowSnackBar(val text: String) : ViewEvent()
}

sealed interface SideEffect {
    object NavigateBack: SideEffect
}