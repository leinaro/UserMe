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
       // return remoteDataSource.getUserContactList()
    //return localDataSource.getUserContactList()
        return flow {
            delay(5000)
            emit(
                listOf(
                    UserContact(
                        id = 0,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 1,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 2,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 3,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 4,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 5,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 6,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 7,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 8,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 9,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    ),
                    UserContact(
                        id = 10,
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    )
                )
            )
        }
    }
}
