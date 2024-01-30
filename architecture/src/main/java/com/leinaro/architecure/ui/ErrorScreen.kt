package com.leinaro.architecure.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leinaro.architecure.R.string

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: (() -> Unit)? = null
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth().padding(24.dp),
    ) {
        Text(
            text = message
        )

        onClickRetry?.let {
            OutlinedButton(onClick = onClickRetry) {
                Text(text = stringResource(string.retry))
            }
        }
    }
}

@Preview
@Composable
private fun ErrorScreenPreview(){
    ErrorScreen(message ="¡Oops! Parece que algo se perdió en el espacio-tiempo.")
}