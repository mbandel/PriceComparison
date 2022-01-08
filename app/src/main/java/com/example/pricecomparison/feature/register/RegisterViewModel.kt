package com.example.pricecomparison.feature.register

import androidx.lifecycle.ViewModel
import com.example.pricecomparison.feature.register.state.RegisterEffect
import com.example.pricecomparison.feature.register.state.RegisterEvent
import com.example.pricecomparison.feature.register.state.RegisterState
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

typealias RegisterProcessor = Processor<RegisterEvent, RegisterState, RegisterEffect>

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    val processor: RegisterProcessor = processor(
        initialState = RegisterState()
    ) { event ->
        when (event) {
            is RegisterEvent.RegisterClick -> TODO()
        }
    }
}
