package com.example.pricecomparison.feature.register.usecase

import com.example.pricecomparison.feature.register.RegisterRepository
import com.example.pricecomparison.feature.register.data.RegisterDTO
import com.example.pricecomparison.feature.register.data.RegisterStatus
import com.example.pricecomparison.feature.register.state.RegisterEffect
import com.example.pricecomparison.feature.register.state.RegisterPartialState
import com.example.pricecomparison.feature.register.state.RegisterState
import com.tomcz.ellipse.EffectsCollector
import com.tomcz.ellipse.PartialState
import com.tomcz.ellipse.common.NoAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: RegisterRepository
) : RegisterUseCase {
    override fun invoke(
        effects: EffectsCollector<RegisterEffect>,
        registerDTO: RegisterDTO
    ): Flow<PartialState<RegisterState>> =
        repository.signUp(registerDTO = registerDTO)
            .map { registerStatus ->
                when (registerStatus) {
                    RegisterStatus.Loading -> RegisterPartialState.ShowProgressBar
                    RegisterStatus.EmailOrUsernameAlreadyExists -> effects.send(
                        RegisterEffect.ShowEmailOrUsernameExists
                    ).let { NoAction() }
                    RegisterStatus.ServerError -> RegisterPartialState.ShowServerError
                    is RegisterStatus.Success -> effects.send(RegisterEffect.GoToCategories)
                        .let { NoAction() }
                }
            }
            .onCompletion { emit(RegisterPartialState.HideProgressBar) }
}
