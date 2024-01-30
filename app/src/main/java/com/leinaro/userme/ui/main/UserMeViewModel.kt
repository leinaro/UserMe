package com.leinaro.userme.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.leinaro.architecure.ui.BaseViewModel
import com.leinaro.architecure.ui.UiState.Loaded
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserMeViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase,
): BaseViewModel<ContactListViewState>() {

    private val _contactsState: MutableStateFlow<PagingData<UserContact>> = MutableStateFlow(value = PagingData.empty())
    val contactsState: StateFlow<PagingData<UserContact>> get() = _contactsState

    fun getAllUsers(){
        viewModelScope.launch {
            getUsers()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect { pagedUserContactList ->
                    _contactsState.value = pagedUserContactList
                _state.value = Loaded<ContactListViewState>(
                    ContactListViewState(contactList = pagedUserContactList)
                )
            }
        }
    }
}

data class ContactListViewState(
    val contactList: PagingData<UserContact> = PagingData.empty(),
)
