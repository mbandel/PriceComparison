package com.example.pricecomparison.feature.login

import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginStatus
import com.example.pricecomparison.feature.login.state.LoginEffect
import com.example.pricecomparison.feature.login.state.LoginPartialState
import com.tomcz.ellipse.EffectsCollector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
) : LoginUseCase {
    override fun invoke(
        credentials: LoginCredentials,
        effects: EffectsCollector<LoginEffect>
    ): Flow<LoginPartialState> =
        loginRepository.authenticate(credentials)
            .map { loginResponse ->
                when (loginResponse) {
                    is LoginStatus.Loading -> LoginPartialState.ShowProgressBar
                    is LoginStatus.ServerError -> {
                        LoginPartialState.ShowServerError
                    }
                    is LoginStatus.IncorrectCredentials -> {
                        LoginPartialState.ShowInvalidCredentialsError
                    }
                    is LoginStatus.Success -> {
                        effects.send(LoginEffect.GoToCategories)
                        LoginPartialState.HideProgressBar
                    }
                }
            }
            .onCompletion { emit(LoginPartialState.HideProgressBar) }
}
