package com.example.pricecomparison

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pricecomparison.feature.login.LoginScreen
import com.example.pricecomparison.feature.login.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(processor = viewModel<LoginViewModel>().processor)
        }
    }
}
