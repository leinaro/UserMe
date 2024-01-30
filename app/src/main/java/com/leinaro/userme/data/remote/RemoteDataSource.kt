package com.leinaro.userme.data.remote

import com.leinaro.userme.data.remote.api.UserMeApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val userMeApi: UserMeApi,
){
    suspend fun getUserContactList() = userMeApi.getUsers(10).results
        .filter {
            it.id.value.isNullOrEmpty().not()
        }
}

