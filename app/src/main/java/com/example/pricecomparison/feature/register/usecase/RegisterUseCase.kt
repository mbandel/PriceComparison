package com.example.pricecomparison.feature.register.usecase

import com.example.pricecomparison.feature.register.data.RegisterDTO
import com.example.pricecomparison.feature.register.state.RegisterEffect
import com.example.pricecomparison.feature.register.state.RegisterState
import com.tomcz.ellipse.EffectsCollector
import com.tomcz.ellipse.PartialState
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    operator fun invoke(
        effects: EffectsCollector<RegisterEffect>,
        registerDTO: RegisterDTO
    ): Flow<PartialState<RegisterState>>
}
