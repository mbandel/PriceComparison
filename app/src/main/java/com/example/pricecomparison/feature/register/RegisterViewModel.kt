package com.example.pricecomparison.feature.register

import androidx.lifecycle.ViewModel
import com.example.pricecomparison.feature.register.data.RegisterDTO
import com.example.pricecomparison.feature.register.state.RegisterEffect
import com.example.pricecomparison.feature.register.state.RegisterEvent
import com.example.pricecomparison.feature.register.state.RegisterPartialState
import com.example.pricecomparison.feature.register.state.RegisterState
import com.example.pricecomparison.feature.register.usecase.RegisterUseCase
import com.tomcz.ellipse.EffectsCollector
import com.tomcz.ellipse.PartialState
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.* // ktlint-disable no-wildcard-imports
import javax.inject.Inject

typealias RegisterProcessor = Processor<RegisterEvent, RegisterState, RegisterEffect>

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    val processor: RegisterProcessor = processor(
        initialState = RegisterState()
    ) { event ->
        when (event) {
            RegisterEvent.RegisterClick -> handleRegisterClick(effects = effects)
            is RegisterEvent.FirstNameChanged -> flowOf(RegisterPartialState.FirstNameChanged(event.firstName))
            is RegisterEvent.LastNameChanged -> flowOf(RegisterPartialState.LastNameChanged(event.lastName))
            is RegisterEvent.UsernameChanged -> flowOf(RegisterPartialState.UsernameChanged(event.username))
            is RegisterEvent.EmailChanged -> flowOf(RegisterPartialState.EmailChanged(event.email))
            is RegisterEvent.PasswordChanged -> flowOf(RegisterPartialState.PasswordChanged(event.password))
            is RegisterEvent.RepeatPasswordChanged -> flowOf(
                RegisterPartialState.RepeatPasswordChanged(
                    event.repeatPassword
                )
            )
        }
    }

    private fun handleRegisterClick(
        effects: EffectsCollector<RegisterEffect>,
    ): Flow<PartialState<RegisterState>> = registerUseCase.invoke(
        registerDTO = RegisterDTO(
            firstName = processor.state.value.firstName,
            lastName = processor.state.value.lastName,
            email = processor.state.value.email,
            username = processor.state.value.username,
            password = processor.state.value.password
        ),
        effects = effects
    )
}
