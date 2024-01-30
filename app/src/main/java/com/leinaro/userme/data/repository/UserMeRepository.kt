package com.leinaro.userme.data.repository

import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.remote.RemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserMeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    //private val localDataSource: LocalDataSource,
) {
    fun getUserContactList(): Flow<List<UserContact>> {
        return flow {
            val contactList = remoteDataSource.getUserContactList().map {

                UserContact(
                    //id = it.id,
                    name = "${it.name.first} ${it.name.last}",
                    email = it.email,
                    profilePicture = it.picture.large,
                    genre = it.gender,
                    registerDate = it.registered.date,
                )
            }

            emit(contactList)
        }
    }
}
