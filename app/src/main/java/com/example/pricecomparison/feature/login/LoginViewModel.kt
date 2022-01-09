package com.example.pricecomparison.feature.login

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.ViewModel
import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginStatus
import com.example.pricecomparison.feature.login.state.LoginEffect
import com.example.pricecomparison.feature.login.state.LoginEvent
import com.example.pricecomparison.feature.login.state.LoginPartialState
import com.example.pricecomparison.feature.login.state.LoginState
import com.example.pricecomparison.util.FieldValidation
import com.tomcz.ellipse.EffectsCollector
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

typealias LoginProcessor = Processor<LoginEvent, LoginState, LoginEffect>

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    val processor: LoginProcessor = processor(
        initialState = LoginState()
    ) { event ->
        when (event) {
            is LoginEvent.EmailChanged -> handleEmailChanged(event.email)
            is LoginEvent.PasswordChanged -> handlePasswordChange(event.password)
            is LoginEvent.LoginClick -> handleLoginClick(
                effects,
                LoginCredentials(event.email, event.password)
            )
            is LoginEvent.ConfirmServerError -> flowOf(LoginPartialState.HideServerError)
            is LoginEvent.ConfirmInvalidCredentialsError -> flowOf(LoginPartialState.HideInvalidCredentialsError)
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

    private fun handleLoginClick(
        effects: EffectsCollector<LoginEffect>,
        credentials: LoginCredentials
    ) = flow {
        loginRepository.authenticate(credentials).collect { loginResponse ->
            when (loginResponse) {
                is LoginStatus.Loading -> emit(LoginPartialState.ShowProgressBar)
                is LoginStatus.ServerError -> {
                    emit(LoginPartialState.ShowServerError)
                    emit(LoginPartialState.HideProgressBar)
                }
                is LoginStatus.IncorrectCredentials -> {
                    emit(LoginPartialState.ShowInvalidCredentialsError)
                    emit(LoginPartialState.HideProgressBar)
                }
                is LoginStatus.Success -> {
                    effects.send(LoginEffect.GoToCategories)
                    emit(LoginPartialState.HideProgressBar)
                }
            }
        }
    }
}
