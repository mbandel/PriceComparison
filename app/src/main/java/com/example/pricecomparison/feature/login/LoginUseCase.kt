package com.example.pricecomparison.feature.login

import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.state.LoginEffect
import com.example.pricecomparison.feature.login.state.LoginPartialState
import com.tomcz.ellipse.EffectsCollector
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    operator fun invoke(
        credentials: LoginCredentials,
        effects: EffectsCollector<LoginEffect>
    ): Flow<LoginPartialState>
}
