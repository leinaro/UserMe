package com.leinaro.userme.data.remote

import com.leinaro.userme.data.remote.api.UserMeApi
import com.leinaro.userme.data.remote.api.UserMeResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val userMeApi: UserMeApi,
){
    suspend fun getUserContactList(
        page: Int = 1,
        size: Int = 50,
    ): UserMeResponse {
        return userMeApi.getUsers(
            size = size,
            page = page,
        ).apply {
            results
                .filter { it.id.value.isNullOrEmpty().not() }
        }
    }
}

