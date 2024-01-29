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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leinaro.userme.R
import com.leinaro.userme.ui.contactlist.ContactListScreen
import com.leinaro.userme.ui.contactlist.ContactListViewState
import com.leinaro.userme.ui.contactlist.UserContact
import com.leinaro.userme.ui.core.MainTopBar
import com.leinaro.userme.ui.theme.UsermeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    var title by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MainTopBar(
                navController = navController,
                title = title,
            )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            navController = navController,
            startDestination = Routes.ContactList.route
        ) {
            composable(Routes.ContactList.route) {
                title = stringResource(R.string.contacts)
                ContactListScreen(
                    navController = navController,
                    state = ContactListViewState(
                        contactList = listOf(
                            UserContact(
                                id = 0,
                                name = "Andrés Martínez",
                                email="andres.mart@gmail.com",
                                profilePicture="https://picsum.photos/200"
                            )
                        )
                    )
                )
            }
            dialog(
                Routes.ContactDetails.route,
                arguments = listOf(navArgument("billId") { type = NavType.LongType })
            ){backStackEntry ->
               /* BillDetailsScreen(
                    navController = navController,
                    billId = backStackEntry.arguments?.getLong("billId") ?: throw NullPointerException()
                )*/
            }
            dialog("detail_dialog") {
                // This content will be automatically added to a Dialog() composable
                // and appear above the HomeScreen or other composable destinations
                //InfoScreen(listOf(Developer("Inés Rojas", "https://www.linkedin.com/in/ingenieraadela/")))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    UsermeTheme {
        MainScreen()
    }
}

sealed class Routes(val route: String) {
    object ContactList: Routes("contact-list")
    object ContactDetails: Routes("contact-details")
    object Info: Routes("info")
}