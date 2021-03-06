package com.example.pricecomparison.feature.categories

import androidx.lifecycle.ViewModel
import com.example.pricecomparison.feature.categories.state.CategoriesEffect
import com.example.pricecomparison.feature.categories.state.CategoriesEvent
import com.example.pricecomparison.feature.categories.state.CategoriesState
import com.example.pricecomparison.feature.categories.usecase.GetCategoriesUseCase
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import com.tomcz.ellipse.common.toNoAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

typealias CategoriesProcessor = Processor<CategoriesEvent, CategoriesState, CategoriesEffect>

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    val processor: CategoriesProcessor = processor(
        initialState = CategoriesState(),
        prepare = { getCategoriesUseCase() },
        onEvent = { event ->
            when (event) {
                is CategoriesEvent.OnItemClick ->
                    effects.send(CategoriesEffect.GoToProduct(event.categoryId)).toNoAction()
            }
        }
    )
}
