package com.leinaro.userme.ui.contactlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.leinaro.userme.R.string

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun UserContactItem(
    userContact: UserContact,
) {
    Column {
        ListItem(
            headlineText = {
                Text(
                    text = userContact.name,
                )
            },
            supportingText = {
                Text(
                    text = userContact.email
                )
            },
            leadingContent = {
                AsyncImage(
                    model = "https://example.com/image.jpg",
                    contentDescription = stringResource(
                        string.profile_picture_name, userContact.name
                    )
                )
            },
            trailingContent = {

            }
        )
        Divider()
       // HorizontalDivider()

    }
}

@Composable
@Preview
private fun UserContactItemPreview(){
    UserContactItem(
        UserContact(
            id = 0,
            name = "Andrés Martínez",
            email="andres.mart@gmail.com",
            profilePicture="https://picsum.photos/200"
        )
    )
}