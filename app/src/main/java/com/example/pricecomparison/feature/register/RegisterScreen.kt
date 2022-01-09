package com.example.pricecomparison.feature.register

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pricecomparison.navigation.Screen
import com.example.pricecomparison.ui.compose.EmailField
import com.example.pricecomparison.ui.compose.Title
import com.example.pricecomparison.ui.theme.LargePadding

@Composable
fun RegisterScreen() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LargePadding)
    ) {

        Title()
        Spacer(modifier = Modifier.height(30.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview () {
    RegisterScreen()
}