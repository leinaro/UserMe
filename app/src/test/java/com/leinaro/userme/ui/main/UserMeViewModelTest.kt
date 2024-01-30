package com.leinaro.userme.ui.main

import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.leinaro.architecure.ui.UiState.Loaded
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.remote.api.CoordinatesResponse
import com.leinaro.userme.data.remote.api.IdResponse
import com.leinaro.userme.data.remote.api.LocationResponse
import com.leinaro.userme.data.remote.api.NameResponse
import com.leinaro.userme.data.remote.api.PictureResponse
import com.leinaro.userme.data.remote.api.RegisteredResponse
import com.leinaro.userme.data.remote.api.UserContactResponse
import com.leinaro.userme.domain.usecase.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
        //Assert.assertEquals("La lista debe tener el mismo tamaño", state.data.contactList, userContactListFlow.first())
        //Assert.assertEquals("La lista debe tener el mismo tamaño", userContactList.size, usersState)
    }

}

val userContactList = listOf(
    UserContact(
        id = "0",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "1",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "2",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "3",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "4",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "5",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "6",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "7",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "8",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "9",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    ),
    UserContact(
        id = "10",
        name = "Andrés Martínez",
        email="andres.mart@gmail.com",
        profilePicture="https://picsum.photos/200",
        genre = "Hombre",
        registerDate = "01/09/2021",
        phone = "(+34) 123 12 12 12",
        latitude = 0.0,
        longitude = 0.0
    )
)

val userContactResponseList = listOf(
    UserContactResponse(
        id = IdResponse(value = "0"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "1"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "2"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "3"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "4"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "5"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "6"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "7"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "8"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "9"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    ),
    UserContactResponse(
        id = IdResponse(value = "10"),
        name = NameResponse(first = "Andrés", last= "Martínez"),
        email="andres.mart@gmail.com",
        gender = "Hombre",
        registered = RegisteredResponse(date="2021-09-01T00:00:00.000Z"),
        picture = PictureResponse(large = "https://picsum.photos/200", medium = "https://picsum.photos/200", thumbnail = "https://picsum.photos/200"),
        phone = "(+34) 123 12 12 12",
        location = LocationResponse(coordinates = CoordinatesResponse("",""))
    )
)

val userContactListFlow = flow {
    emit(
        PagingData.from(userContactList)
    )
}