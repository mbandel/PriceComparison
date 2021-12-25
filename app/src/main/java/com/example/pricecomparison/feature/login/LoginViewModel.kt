package com.example.pricecomparison.feature.login

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.ViewModel
import com.example.pricecomparison.feature.login.state.LoginEffect
import com.example.pricecomparison.feature.login.state.LoginEvent
import com.example.pricecomparison.feature.login.state.LoginPartialState
import com.example.pricecomparison.feature.login.state.LoginState
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias LoginProcessor = Processor<LoginEvent, LoginState, LoginEffect>

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    val processor: LoginProcessor = processor(initialState = LoginState()) { event ->
        when (event) {
            is LoginEvent.EmailChanged -> handleEmailChanged(event.email)
            is LoginEvent.PasswordChanged -> handlePasswordChange(event.password)
            is LoginEvent.LoginClick -> TODO()
        }
    }

    private fun handleEmailChanged(email: String) = flow {
        emit(LoginPartialState.EmailChanged(email))
        if (isEmailValid(email)) {
            emit(LoginPartialState.HideEmailError)
        } else {
            emit(LoginPartialState.ShowEmailError)
        }
    }

    private fun handlePasswordChange(password: String) = flow {
        emit(LoginPartialState.PasswordChanged(password))
        if (isPasswordValid(password)) {
            emit(LoginPartialState.HidePasswordError)
        } else {
            emit(LoginPartialState.ShowPasswordError)
        }
    }

    private fun isEmailValid(email: String): Boolean =
        EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}\$"
        return password.matches(Regex(passwordPattern))
    }
}
