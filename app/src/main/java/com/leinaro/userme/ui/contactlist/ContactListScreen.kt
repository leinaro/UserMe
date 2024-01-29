package com.leinaro.userme.ui.contactlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

data class ContactListViewState(
    val contactList: List<UserContact> = listOf(),
)

data class UserContact(
    val id: Long,
    val profilePicture: String,
    val name: String,
    val email: String
)

@Composable
fun ContactListScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    state: ContactListViewState,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(state.contactList){ userContact ->
            UserContactItem(
                userContact = userContact,
            )
        }
    }
}

@Composable
@Preview
private fun ContactListScreenPreview(){
    ContactListScreen(
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
