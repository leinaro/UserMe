package com.leinaro.userme.data.remote

import androidx.paging.PagingSource
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.remote.api.UserMeResponse
import com.leinaro.userme.ui.main.userContactList
import com.leinaro.userme.ui.main.userContactResponseList
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
class UserContactPagingSourceTest {

    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)
    private val remoteDataSource = mockk<RemoteDataSource>(relaxed = true)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        val response = mockk<UserMeResponse>(relaxed = true)
        every { response.results } returns userContactResponseList
        coEvery { remoteDataSource.getUserContactList(any(), any()) } returns response
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when users are given then users paging source returns success load result`() =
        testScope.runTest {
            // given
            val usersPagingSource = UserContactPagingSource(
                remoteDataSource = remoteDataSource
            )

            val params = PagingSource
                .LoadParams
                .Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Page(
                    data = userContactList,
                    prevKey = -1,
                    nextKey = 1
                )

            // when
            val actual = usersPagingSource.load(params = params)

            // then
            assertEquals(expected, actual)
        }

    @Test
    fun `when users are given then users paging source returns error load result`() =
        testScope.runTest {
            // given
            val usersPagingSource = UserContactPagingSource(
                remoteDataSource = remoteDataSource
            )

            val exception = mockk<HttpException>(relaxed = true)
            coEvery { remoteDataSource.getUserContactList(any(), any()) } throws exception

            val params = PagingSource
                .LoadParams
                .Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Error<Int, UserContact>(
                    throwable = exception
                )::class.java

            // when

            val actual = usersPagingSource.load(params = params)::class.java

            // then
            assertEquals(expected, actual)
        }

    @Test
    fun `when users are given for next pages then users paging source returns success append load result`() =
        testScope.runTest {
            // given
            val usersPagingSource = UserContactPagingSource(
                remoteDataSource = remoteDataSource
            )

            val params = PagingSource
                .LoadParams
                .Append(
                    key = 20,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Page(
                    data = userContactList,
                    prevKey = 19,
                    nextKey = 1
                )

            // when
            val actual = usersPagingSource.load(params = params)

            // then
            assertEquals(expected, actual)
        }
}