package com.leinaro.userme.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.leinaro.userme.data.mapper.toDomain
import com.leinaro.userme.data.model.UserContact
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserContactPagingSource @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : PagingSource<Int, UserContact>() {

    var query: String? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserContact> {
        return try {
            val currentPage = params.key ?: 1
            val response = remoteDataSource.getUserContactList(
                size = params.loadSize,
                page = currentPage
            )
            LoadResult.Page(
                data = response.results
                    .map { it.toDomain() }
                    .filter { user ->
                        user.id.isEmpty().not() && query?.let { q ->
                            user.name.contains(q) || user.email.contains(q)
                        }?: true
                    },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.results.isEmpty()) null else response.info.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserContact>): Int? {
        return state.anchorPosition
    }

}