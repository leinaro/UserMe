package com.leinaro.userme.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.leinaro.userme.R
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.ui.contactlist.ContactListScreen
import com.leinaro.userme.ui.core.MainTopBar
import com.leinaro.userme.ui.info.Developer
import com.leinaro.userme.ui.info.InfoScreen
import com.leinaro.userme.ui.theme.UsermeTheme
import com.leinaro.userme.ui.usercontactdetails.UserContactDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: ContactListViewState,
    navController: NavHostController = rememberNavController(),
    viewModel: UserMeViewModel = viewModel()
    ) {
    var title by remember { mutableStateOf("") }

    val userContactItems: LazyPagingItems<UserContact> = viewModel.contactsState.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MainTopBar(
                navController = navController,
                title = title,
            )
        },
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Routes.ContactList.route
        ) {
            composable(Routes.ContactList.route) {
                title = stringResource(R.string.contacts)
                ContactListScreen(
                    modifier = Modifier.padding(paddingValues),
                    navController = navController,
                    contactList = userContactItems,
                )
            }
            composable(
                Routes.ContactDetails.route,
                arguments = listOf(
                    navArgument("userId") { type = NavType.StringType },
                    navArgument("index") { type = NavType.IntType },
                )
            ){backStackEntry ->
                val userId = backStackEntry.arguments?.getString("userId") ?: throw NullPointerException("User id not found")
                val selectedPosition = backStackEntry.arguments?.getInt("index") ?: throw NullPointerException("Index not found")

                val userContact = userContactItems[selectedPosition] ?: throw NullPointerException("User with id $userId not found")

                title = userContact.name
                UserContactDetails(
                    navController = navController,
                    userContact = userContact
                )
            }
            dialog(Routes.Info.route) {
                InfoScreen(listOf(
                    Developer("Inés Rojas", "https://www.linkedin.com/in/ingenieraadela/", "https://github.com/leinaro")
                ))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    UsermeTheme {
        MainScreen(
            state = ContactListViewState(
                contactList = PagingData.from(listOf(
                    UserContact(
                        id = "0",
                        name = "Andrés Martínez",
                        email="andres.mart@gmail.com",
                        profilePicture="https://picsum.photos/200"
                    )
                ))
            )
        )
    }
}

sealed class Routes(val route: String) {
    object ContactList: Routes("contact-list")
    object ContactDetails: Routes("contact-details/{userId}/{index}")
    object Info: Routes("info")
}