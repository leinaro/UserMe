package com.leinaro.userme.domain.usecase

import androidx.paging.PagingData
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.repository.UserMeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserMeRepository,
) {
    operator fun invoke(query: String? = null): Flow<PagingData<UserContact>> {
        return repository.getUserContactList(query = query)
    }
}

