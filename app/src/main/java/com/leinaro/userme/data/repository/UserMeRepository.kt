package com.leinaro.userme.data.repository

import android.provider.SyncStateContract.Constants
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.leinaro.userme.data.mapper.toDomain
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.remote.RemoteDataSource
import com.leinaro.userme.data.remote.UserContactPagingSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class UserMeRepository @Inject constructor(
    private val userContactPagingSource: UserContactPagingSource,
   // private val remoteDataSource: RemoteDataSource,
    //private val localDataSource: LocalDataSource,
) {
    /*fun getUserContactList(): Flow<List<UserContact>> {
        return flow {
            val contactList = remoteDataSource.getUserContactList()
                .results
            .filter {
                it.id.value.isNullOrEmpty().not()
            }.map {
                it.toDomain()
            }

            emit(contactList)
        }
    }*/

    fun getUserContactList(): Flow<PagingData<UserContact>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = {
                userContactPagingSource
            }
        ).flow
    }
}
