package com.leinaro.userme.ui.usercontactdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.leinaro.userme.R.drawable
import com.leinaro.userme.R.string
import com.leinaro.userme.data.model.UserContact

@Composable
fun UserContactDetails(
    navController: NavHostController = rememberNavController(),
    userContact: UserContact,
) {
    var name by rememberSaveable { mutableStateOf(userContact.name) }
    var email by rememberSaveable { mutableStateOf(userContact.email) }
    var genre by rememberSaveable { mutableStateOf(userContact.genre) }
    var registerDate by rememberSaveable { mutableStateOf(userContact.registerDate) }
    var phone by rememberSaveable { mutableStateOf(userContact.phone) }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState),
        ) {

        UserActionsBar(
            profilePicture = userContact.profilePicture,
            name = userContact.name,
        )

        UserTextField(
            readOnly = true,
            value = name,
            onValueChange = { name = it },
            label = stringResource(string.name_and_last_name),
            leadingIcon =  painterResource(id = drawable.ic_user),
        )

        UserTextField(
            readOnly = true,
            value = email,
            onValueChange = { email = it },
            label = stringResource(string.email),
            leadingIcon =  painterResource(id = drawable.ic_mail),
        )

        UserTextField(
            readOnly = true,
            value = genre,
            onValueChange = { genre = it },
            label = stringResource(string.genre),
            leadingIcon =  painterResource(id = drawable.ic_gender),
        )
        UserTextField(
            readOnly = true,
            value = registerDate,
            onValueChange = { registerDate = it },
            label = stringResource(string.register_date),
            leadingIcon =  painterResource(id = drawable.ic_calendar),
        )
        UserTextField(
            readOnly = true,
            value = phone,
            onValueChange = { phone = it },
            label = stringResource(string.phone),
            leadingIcon =  painterResource(id = drawable.ic_phone),
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun UserContactDetailsPreview(){
    UserContactDetails(
        userContact = UserContact(
            id = "0",
            name = "Andrés Martínez",
            email="andres.mart@gmail.com",
            profilePicture="https://picsum.photos/200",
            genre = "Hombre",
            registerDate = "2021-09-01T00:00:00.000Z",
            phone = "123456789",
            latitude = 0.0,
            longitude = 0.0,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    readOnly: Boolean = false,
    label: String = "",
    leadingIcon: Painter,
) {

    CustomTextField(
        readOnly = readOnly,
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style=TextStyle(
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    // fontFamily = FontFamily(Font(font.open sans)),
                    fontWeight = FontWeight(400),
                )
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color(0xFFE7E7E7),
            unfocusedIndicatorColor = Color(0xFFE7E7E7),
            disabledIndicatorColor = Color(0xFFE7E7E7)
        ),
        leadingIcon = {
            Icon(
                painter = leadingIcon,
                contentDescription = null
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle  = TextStyle(
            fontSize = 14.sp,
            lineHeight = 16.sp,
            // fontFamily = FontFamily(Font(R.font.open sans)),
            fontWeight = FontWeight(600),
            //   color = Color(0xFF000000),

        )
    )
}

