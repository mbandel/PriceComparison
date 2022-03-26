package com.example.pricecomparison

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pricecomparison.ui.compose.TabsLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val viewModel: LoginViewModel by viewModels()
        setContent {
//            LoginScreen(processor = viewModel<LoginViewModel>().processor)
            TabsLayout()
        }
    }
}
