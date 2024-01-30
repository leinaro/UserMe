package com.leinaro.userme.domain.usecase

import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.repository.UserMeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserMeRepository,
) {
    operator fun invoke(): Flow<List<UserContact>> {
        return repository.getUserContactList()
    }
}

