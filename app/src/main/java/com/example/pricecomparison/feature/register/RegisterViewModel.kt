package com.example.pricecomparison.feature.register

import androidx.lifecycle.ViewModel
import com.example.pricecomparison.feature.register.data.RegisterDTO
import com.example.pricecomparison.feature.register.data.RegisterStatus
import com.example.pricecomparison.feature.register.state.RegisterEffect
import com.example.pricecomparison.feature.register.state.RegisterEvent
import com.example.pricecomparison.feature.register.state.RegisterPartialState
import com.example.pricecomparison.feature.register.state.RegisterState
import com.tomcz.ellipse.EffectsCollector
import com.tomcz.ellipse.PartialState
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.NoAction
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

typealias RegisterProcessor = Processor<RegisterEvent, RegisterState, RegisterEffect>

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {
    val processor: RegisterProcessor = processor(
        initialState = RegisterState()
    ) { event ->
        when (event) {
            is RegisterEvent.RegisterClick -> TODO()
            is RegisterEvent.FirstNameChanged -> flowOf(RegisterPartialState.FirstNameChanged(event.firstName))
            is RegisterEvent.LastNameChanged -> flowOf(RegisterPartialState.LastNameChanged(event.lastName))
            is RegisterEvent.UsernameChanged -> flowOf(RegisterPartialState.UsernameChanged(event.username))
            is RegisterEvent.EmailChanged -> flowOf(RegisterPartialState.EmailChanged(event.email))
            is RegisterEvent.PasswordChanged -> flowOf(RegisterPartialState.PasswordChanged(event.password))
            is RegisterEvent.RepeatPasswordChanged -> flowOf(RegisterPartialState.RepeatPasswordChanged(event.repeatPassword))
        }
    }

    private fun handleRegisterClick(
        effects: EffectsCollector<RegisterEffect>,
        registerDTO: RegisterDTO
    ): Flow<PartialState<RegisterState>> = repository.signUp(registerDTO = registerDTO)
        .map { registerStatus ->
            when (registerStatus) {
                RegisterStatus.Loading -> RegisterPartialState.ShowProgressBar
                RegisterStatus.EmailOrUsernameAlreadyExists -> TODO()
                RegisterStatus.ServerError -> RegisterPartialState.ShowServerError
                is RegisterStatus.Success -> effects.send(RegisterEffect.GoToCategories).let { NoAction() }
            }
        }
        .onCompletion { emit(RegisterPartialState.HideProgressBar) }
}
