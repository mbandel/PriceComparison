package com.example.pricecomparison.feature.register.state

import com.tomcz.ellipse.PartialState

sealed interface RegisterPartialState : PartialState<RegisterState> {
    data class FirstNameChanged(val firstName: String) : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(firstName = firstName)
    }

    data class LastNameChanged(val lastName: String) : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(lastName = lastName)
    }

    data class UsernameChanged(val username: String) : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(username = username)
    }

    data class EmailChanged(val email: String) : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(email = email)
    }

    data class PasswordChanged(val password: String) : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(password = password)
    }

    data class RepeatPasswordChanged(val repeatPassword: String) : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(repeatPassword = repeatPassword)
    }

    object ShowProgressBar : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(isLoading = true)
    }

    object HideProgressBar : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(isLoading = false)
    }

    object ShowServerError : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(isShowingServerError = true)
    }

    object HideServerError : RegisterPartialState {
        override fun reduce(oldState: RegisterState): RegisterState =
            oldState.copy(isShowingServerError = false)
    }
}
