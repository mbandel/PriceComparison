package com.example.pricecomparison.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.pricecomparison.R
import com.example.pricecomparison.ui.theme.SmallPadding

@Composable
fun ErrorSnackBar(message: String, sendEvent: () -> Unit) {
    Snackbar(
        modifier = Modifier.padding(SmallPadding),
        action = {
            Button(onClick = sendEvent) {
                Text(text = stringResource(id = R.string.login_ok))
            }
        }
    ) {
        Text(text = message)
    }
}
