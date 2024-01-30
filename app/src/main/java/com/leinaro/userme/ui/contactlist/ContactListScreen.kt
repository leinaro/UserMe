package com.leinaro.userme.ui.contactlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.ui.main.ContactListViewState
import com.leinaro.userme.ui.main.Routes
import com.leinaro.userme.ui.main.UserMeViewModel

@Composable
fun ContactListScreen(
    navController: NavHostController = rememberNavController(),
    contactList: List<UserContact>,
    //viewModel: UserMeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(19.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(17.dp).fillMaxWidth())
        }
        items(contactList){ userContact ->
            UserContactItem(
                userContact = userContact,
                onUserContactClick = {
                    navController.navigate(Routes.ContactDetails.route)
                }
            )
        }
    }
}

@Composable
@Preview
private fun ContactListScreenPreview(){
    ContactListScreen(
        contactList = listOf(
            UserContact(
                id = 0,
                name = "Andrés Martínez",
                email="andres.mart@gmail.com",
                profilePicture="https://picsum.photos/200"
            )
        )
    )
}
