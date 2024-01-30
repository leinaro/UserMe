package com.leinaro.userme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.leinaro.architecure.ui.ErrorScreen
import com.leinaro.architecure.ui.LoadingScreen
import com.leinaro.architecure.ui.UiState.Error
import com.leinaro.architecure.ui.UiState.Idle
import com.leinaro.architecure.ui.UiState.Loaded
import com.leinaro.architecure.ui.UiState.Loading
import com.leinaro.userme.R.string
import com.leinaro.userme.ui.main.MainScreen
import com.leinaro.userme.ui.main.UserMeViewModel
import com.leinaro.userme.ui.theme.UsermeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsermeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: UserMeViewModel = hiltViewModel()

                    val viewState by viewModel.state.collectAsState()

                    LaunchedEffect(key1 = Unit){
                        viewModel.getAllUsers()
                    }

                    when(val state = viewState) {
                        is Loading -> LoadingScreen()
                        is Error -> ErrorScreen(
                            message = getString(string.default_error)
                        )
                        is Loaded -> MainScreen(
                            state = state.data,
                        )
                        is Idle -> Unit
                    }
                }
            }
        }
    }
}