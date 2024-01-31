package com.leinaro.userme.ui.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leinaro.userme.R
import com.leinaro.userme.R.string
import com.leinaro.userme.ui.main.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    navController: NavController = rememberNavController(),
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    query: String = "",
    onValueChange: (String) -> Unit = {},
) {
    val currentBackState by navController.currentBackStackEntryAsState()

    val color = if (currentBackState?.destination?.route == Routes.ContactDetails.route) {
        Color.White
    }
    else {
        Color.Black
    }

    var expanded by remember { mutableStateOf(false) }

    TextFieldDefaults.MinHeight
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            if (currentBackState?.destination?.route == Routes.Search.route){
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = query,
                    onValueChange = onValueChange,
                    shape = RoundedCornerShape(25.dp),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        //fontFamily = FontFamily(Font(font.oswald)),
                        fontWeight = FontWeight(500),
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                            tint = color
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(string.search_by),
                            style = TextStyle(
                                fontSize = 16.sp,
                                //fontFamily = FontFamily(Font(font.oswald)),
                                fontWeight = FontWeight(500),
                            ),
                            color = color
                        )
                    }
                )
            } else {
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
            }
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
            IconButton(onClick = {
                expanded = !expanded
            }) {
                Icon(
                    imageVector = Filled.MoreVert,
                    contentDescription = "Localized description",
                    tint = color
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                if (currentBackState?.destination?.route == Routes.ContactList.route){
                    DropdownMenuItem(
                        text = { Text("Buscar") },
                        onClick = {
                            navController.navigate(Routes.Search.route)
                            expanded = false
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Search,
                                contentDescription = null
                            )
                        })
                }

                DropdownMenuItem(
                    text = { Text("Sobre UserMe") },
                    onClick = {
                        navController.navigate(Routes.Info.route)
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Info,
                            contentDescription = null
                        )
                    })
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