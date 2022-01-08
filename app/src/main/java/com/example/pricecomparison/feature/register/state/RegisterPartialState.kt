package com.example.pricecomparison.feature.register.state

import com.tomcz.ellipse.PartialState

sealed interface RegisterPartialState : PartialState<RegisterState> {
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
}
