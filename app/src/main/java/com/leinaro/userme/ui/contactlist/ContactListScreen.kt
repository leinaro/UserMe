package com.leinaro.userme.ui.contactlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.paging.compose.items
import com.leinaro.architecure.ui.LoadingScreen
import com.leinaro.userme.R
import com.leinaro.userme.R.string
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.ui.main.ContactListViewState
import com.leinaro.userme.ui.main.Routes
import com.leinaro.userme.ui.main.UserMeViewModel

@Composable
fun ContactListScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    contactList: LazyPagingItems<UserContact>,
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(19.dp),
    ) {
        item {
            Spacer(modifier = Modifier
                .height(17.dp)
                .fillMaxWidth())
        }
        items(
            count = contactList.itemCount,
            key = contactList.itemKey(),
            contentType = contactList.itemContentType()
        ) { index ->
            val userContact = contactList[index]
            userContact?.let {
                UserContactItem(
                    userContact = userContact,
                    onUserContactClick = {
                        navController.navigate(
                            Routes.ContactDetails.route
                                .replace("{userId}", userContact.id)
                                .replace("{index}", index.toString())
                        )
                    }
                )
            }
        }

        contactList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingScreen(
                        modifier = Modifier.fillParentMaxSize(),
                        message = stringResource(string.loading_data_message)
                    ) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = contactList.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage?: stringResource(id = R.string.default_error),
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = contactList.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}
/*
@Composable
@Preview
private fun ContactListScreenPreview(){
    ContactListScreen(
        contactList = LazyPagingItems(PagingData.from(listOf(
            UserContact(
                id = "0",
                name = "Andrés Martínez",
                email="andres.mart@gmail.com",
                profilePicture="https://picsum.photos/200"
            )
        )))
    )
}*/

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Reintentar")
        }
    }
}

@Preview
@Composable
fun ErrorMessagePreview() {
    ErrorMessage(
        message = "Error al cargar los datos",
        onClickRetry = {}
    )
}
