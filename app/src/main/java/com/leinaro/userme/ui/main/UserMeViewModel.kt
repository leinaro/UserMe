package com.leinaro.userme.ui.main

import androidx.lifecycle.viewModelScope
import com.leinaro.architecure.ui.BaseViewModel
import com.leinaro.architecure.ui.UiState.Loaded
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserMeViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase,
): BaseViewModel<ContactListViewState>() {

    fun getAllUsers(){
        viewModelScope.launch {
            getUsers().collect { userContactList ->
                _state.value = Loaded<ContactListViewState>(
                    ContactListViewState(contactList = userContactList)
                )
            }
        }
    }

    fun getUser(userId: String?): UserContact? {
        val contactViewState = (state.value as? Loaded<ContactListViewState>)?.data
        return contactViewState?.contactList?.firstOrNull { it.id == userId }
    }
}

data class ContactListViewState(
    val contactList: List<UserContact> = listOf(),
)
