package com.leinaro.userme.data.repository

import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.remote.RemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class UserMeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    //private val localDataSource: LocalDataSource,
) {
    fun getUserContactList(): Flow<List<UserContact>> {
        return flow {
            val contactList = remoteDataSource.getUserContactList().map {

                val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                val targetFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                val formattedDate = try {
                    originalFormat.parse(it.registered.date)?.let { date ->
                        targetFormat.format(date)
                    }
                } catch (e:Exception){
                    it.registered.date
                }.orEmpty()

                UserContact(
                    id = it.id.value.orEmpty(),
                    name = "${it.name.first} ${it.name.last}",
                    email = it.email,
                    profilePicture = it.picture.large,
                    genre = it.gender,
                    registerDate = formattedDate,
                    phone = it.phone,
                )
            }

            emit(contactList)
        }
    }
}
