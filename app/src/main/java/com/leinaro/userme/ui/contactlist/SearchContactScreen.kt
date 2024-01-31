package com.leinaro.userme.ui.contactlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.leinaro.architecure.ui.ErrorScreen
import com.leinaro.userme.R.string
import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.ui.main.Routes

@Composable
fun SearchContactScreen(
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
                /*TODO: Empty state
                   loadState.refresh is LoadState.Loading -> {
                    item { LoadingScreen(
                        modifier = Modifier.fillParentMaxSize(),
                        message = stringResource(string.loading_data_message)
                    ) }
                }*/

                loadState.refresh is LoadState.Error -> {
                    item {
                        ErrorScreen(
                            modifier = Modifier.fillParentMaxSize(),
                            message = stringResource(string.default_error),
                            onClickRetry = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = contactList.loadState.append as LoadState.Error
                    item {
                        ErrorScreen(
                            modifier = Modifier.fillParentMaxSize(),
                            message = stringResource(string.default_error),
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}