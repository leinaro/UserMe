package com.leinaro.userme.data.repository

import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.remote.RemoteDataSource
import com.leinaro.userme.data.remote.UserContactPagingSource
import com.leinaro.userme.data.remote.api.UserMeResponse
import com.leinaro.userme.ui.main.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class VinilosRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val remoteDataSource: RemoteDataSource = mockk(relaxed = true)
    //private val userContactPagingSource: UserContactPagingSource = mockk(relaxed = true)

    private lateinit var repository: UserMeRepository

    @Before
    fun setUp() {

        repository = UserMeRepository(
            userContactPagingSource = UserContactPagingSource(remoteDataSource),
        )
    }

    @Ignore
    @Test
    fun `test getUsers`() = runTest {
        // given
        val users = emptyList<UserContact>()
        val remoteAlbums = listOf(
            mockk<UserContact>(relaxed = true),
            mockk<UserContact>(relaxed = true),
            mockk<UserContact>(relaxed = true),
        )
        val flowEmissions = listOf(users, remoteAlbums)

        val userResponse = mockk<UserMeResponse>(relaxed = true)

        coEvery { remoteDataSource.getUserContactList(any(), any()) } returns userResponse

        // when
        val response = repository.getUserContactList().toList()
        this.testScheduler.advanceUntilIdle()

        // then
        Assert.assertEquals(flowEmissions, response)
        coVerify(exactly = 1) { remoteDataSource.getUserContactList() }
    }
}
