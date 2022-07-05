package com.example.pricecomparison.feature.login

import androidx.lifecycle.ViewModel
import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.state.LoginEffect
import com.example.pricecomparison.feature.login.state.LoginEvent
import com.example.pricecomparison.feature.login.state.LoginPartialState
import com.example.pricecomparison.feature.login.state.LoginState
import com.example.pricecomparison.util.FieldValidation
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

typealias LoginProcessor = Processor<LoginEvent, LoginState, LoginEffect>

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    val processor: LoginProcessor = processor(
        initialState = LoginState()
    ) { event ->
        when (event) {
            is LoginEvent.EmailChanged -> handleEmailChanged(event.email)
            is LoginEvent.PasswordChanged -> handlePasswordChange(event.password)
            is LoginEvent.ConfirmServerError -> flowOf(LoginPartialState.HideServerError)
            is LoginEvent.ConfirmInvalidCredentialsError -> flowOf(LoginPartialState.HideInvalidCredentialsError)
            is LoginEvent.LoginClick -> loginUseCase(
                effects = effects,
                credentials = LoginCredentials(event.email, event.password)
            )
        }
    }

    private fun handleEmailChanged(email: String) = flow {
        emit(LoginPartialState.EmailChanged(email))
        if (FieldValidation.isEmailValid(email)) {
            emit(LoginPartialState.HideEmailError)
        } else {
            emit(LoginPartialState.ShowEmailError)
        }
    }

    private fun handlePasswordChange(password: String) = flow {
        emit(LoginPartialState.PasswordChanged(password))
        if (FieldValidation.isPasswordValid(password)) {
            emit(LoginPartialState.HidePasswordError)
        } else {
            emit(LoginPartialState.ShowPasswordError)
        }
    }
}
