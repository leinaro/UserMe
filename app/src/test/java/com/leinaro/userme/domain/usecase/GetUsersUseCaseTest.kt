package com.leinaro.userme.domain.usecase

import com.leinaro.architecure.ui.UiState.Loaded
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.repository.UserMeRepository
import com.leinaro.userme.ui.main.UserMeViewModel
import com.leinaro.userme.ui.main.userContactList
import com.leinaro.userme.ui.main.userContactListFlow
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {

    private val repository: UserMeRepository = mockk(relaxed = true)
    private lateinit var useCase: GetUsersUseCase

    @Before
    fun setUp() {
        coEvery { repository.getUserContactList() } returns flow{ emptyList<UserContact>()}

        useCase = GetUsersUseCase(repository)
    }

    @Test
    fun `test get all information`() = runTest {
        // given
        coEvery { repository.getUserContactList() } returns userContactListFlow

        // when
        val useCaseResult = useCase()
        this.testScheduler.advanceUntilIdle()

        // then
        coVerify(exactly = 1) { repository.getUserContactList() }
        Assert.assertEquals(userContactListFlow, useCaseResult)
    }
}