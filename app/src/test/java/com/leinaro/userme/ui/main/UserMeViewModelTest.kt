package com.leinaro.userme.ui.main

import com.leinaro.architecure.ui.UiState.Loaded
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.domain.usecase.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserMeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getUsersUseCase: GetUsersUseCase = mockk(relaxed = true)
    private lateinit var viewModel: UserMeViewModel



    @Before
    fun setUp() {
        coEvery { getUsersUseCase() } returns flow{ emptyList<UserContact>()}

        viewModel = UserMeViewModel(getUsersUseCase)
    }

    @Test
    fun `test get all information`() = runTest {
        // given
        coEvery { getUsersUseCase() } returns userContactListFlow

        // when
        viewModel.getAllUsers()
        this.testScheduler.advanceUntilIdle()

        // then
        coVerify(exactly = 1) { getUsersUseCase() }
        Assert.assertTrue(viewModel.state.value is Loaded)
        val state = viewModel.state.value as Loaded
        Assert.assertEquals("La lista debe tener el mismo tamaño", userContactList.size, state.data.contactList.size)
    }

}

val userContactList = listOf(
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

val userContactListFlow = flow {
    emit(
        userContactList
    )
}