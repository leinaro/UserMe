package com.leinaro.userme.ui.contactlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.leinaro.userme.R.string
import com.leinaro.userme.data.model.UserContact

@Composable fun UserContactItem(
    userContact: UserContact,
    onUserContactClick: (UserContact) -> Unit = {},
) {
    Row(
        Modifier
            .padding(start = 16.dp)
            .fillMaxWidth()
            .clickable { onUserContactClick(userContact) },
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(userContact.profilePicture)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(
                string.profile_picture_name, userContact.name
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(52.dp)
                .clip(CircleShape)
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }

        Column {
            Row(
                modifier = Modifier.padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = userContact.name,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 21.sp,
                            //  fontFamily = FontFamily(Font(R.font.sf pro text)),
                            fontWeight = FontWeight(600),
                            //  color = Color(0xFF000000),
                        )
                    )
                    Text(
                        text = userContact.email,
                        style = TextStyle(
                            fontSize = 14.sp,
                            //fontFamily = FontFamily(Font(R.font.sf pro text)),
                            fontWeight = FontWeight(400),
                            //color = Color(0xFF8E8E93),
                        )
                    )
                }

                Spacer(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) // height and background only for demonstration

                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Localized description"
                )
            }
            Divider(
                modifier = Modifier.padding(top=16.dp)
            )
        }
    }
}

@Composable
@Preview
private fun UserContactItemPreview(){
    UserContactItem(
        UserContact(
            id = "0",
            name = "Andrés Martínez",
            email="andres.mart@gmail.com",
            profilePicture="https://picsum.photos/200"
        )
    )
}