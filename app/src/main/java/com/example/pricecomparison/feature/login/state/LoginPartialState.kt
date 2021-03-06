package com.example.pricecomparison.feature.login.state

import com.tomcz.ellipse.PartialState

sealed interface LoginPartialState : PartialState<LoginState> {
    data class EmailChanged(val email: String) : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(email = email)
    }

    data class PasswordChanged(val password: String) : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(password = password)
    }

    object ShowEmailError : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isEmailCorrect = false)
    }

    object HideEmailError : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isEmailCorrect = true)
    }

    object ShowPasswordError : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isPasswordCorrect = false)
    }

    object HidePasswordError : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isPasswordCorrect = true)
    }

    object ShowProgressBar : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isLoading = true)
    }

    object HideProgressBar : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isLoading = false)
    }

    object ShowServerError : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isShowingServerError = true)
    }

    object HideServerError : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isShowingServerError = false)
    }

    object ShowInvalidCredentialsError : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isShowingInvalidCredentialsError = true)
    }

    object HideInvalidCredentialsError : LoginPartialState {
        override fun reduce(oldState: LoginState): LoginState =
            oldState.copy(isShowingInvalidCredentialsError = false)
    }
}
