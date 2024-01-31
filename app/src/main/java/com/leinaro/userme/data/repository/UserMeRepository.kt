package com.leinaro.userme.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.remote.UserContactPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserMeRepository @Inject constructor(
    private val userContactPagingSource: UserContactPagingSource,
) {

    fun getUserContactList(query: String? = null): Flow<PagingData<UserContact>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = {
                userContactPagingSource.apply {
                    this.query = query
                }
            }
        ).flow
    }
}
