package com.leinaro.architecure.ui

/*import com.leinaro.ledger.common.ViewHandler

typealias ViewDataState<T> = Pair<T, ViewHandler<out T, out com.leinaro.architecure.ui.BaseViewModel<T>>>
typealias ViewEventState<T> = Pair<com.leinaro.architecure.ui.ViewEvent, ViewHandler<out com.leinaro.architecure.ui.ViewEvent, out com.leinaro.architecure.ui.BaseViewModel<T>>>
*/
sealed class UiState<out T : Any> {
    data class Loaded<out T : Any>(val data: T): UiState<T>()
    object Loading: UiState<Nothing>()
    data class Error(val message: String): UiState<Nothing>()
    object Idle: UiState<Nothing>()
}