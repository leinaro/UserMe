package com.leinaro.userme.ui.core

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leinaro.userme.R
import com.leinaro.userme.R.drawable
import com.leinaro.userme.ui.main.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    navController: NavController = rememberNavController(),
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val currentBackState by navController.currentBackStackEntryAsState()

    var color = if (currentBackState?.destination?.route == Routes.ContactDetails.route) {
        Color.White
    }
    else {
        Color.Black
    }

    /*LaunchedEffect(key1 = currentBackState){
        Log.i("iarl", "---> currentBackState: ${currentBackState?.destination?.route}")
        color = if (currentBackState?.destination?.route == Routes.ContactDetails.route) {
            Color.White
        }
        else {
            Color.Black
        }
    }*/
    Log.i("iarl", "currentBackState: ${currentBackState}")

    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 16.sp,
                    //fontFamily = FontFamily(Font(font.oswald)),
                    fontWeight = FontWeight(500),
                ),
                color = color
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description",
                    tint = color
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate("detail_dialog") }) {
                Icon(
                    imageVector = Filled.MoreVert,
                    contentDescription = "Localized description",
                    tint = color
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainTopBarPreview(){
    MainTopBar(title = stringResource(id = R.string.contacts))
}