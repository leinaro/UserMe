package com.leinaro.userme.ui.usercontactdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Error
import coil.compose.AsyncImagePainter.State.Loading
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest.Builder
import com.leinaro.userme.R
import com.leinaro.userme.R.drawable
import com.leinaro.userme.R.string

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserActionsBar(
    profilePicture: String = "",
    name: String = ""
){

    Box{
        Column {
            /*AnimatedVisibility(
                     visible = scrollState.isScrollingUp().value,
                     //enter = //TODO: your enter animation,
                     //exit = //TODO: your exit animation
                 ) {*/
               Image(
                   painter = painterResource(id = drawable.rectangle ),
                   contentDescription = null,
                   modifier = Modifier.fillMaxWidth(),
                   colorFilter = null,
                   contentScale = ContentScale.FillWidth
               )
               //}
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "Agregar foto"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "Editar"
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 17.dp)
                .clip(CircleShape)
                .background(color = Color.White)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            SubcomposeAsyncImage(
                model = Builder(LocalContext.current)
                    .data(profilePicture)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(
                    string.profile_picture_name, name
                ),
                modifier = Modifier
                    .size(77.dp)
                    .clip(CircleShape)
            ) {
                val state = painter.state
                if (state is Loading || state is Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
        }
    }
}

@Preview
@Composable
fun UserActionsBarPreview(){
    UserActionsBar()
}