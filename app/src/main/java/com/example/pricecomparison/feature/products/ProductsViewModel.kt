package com.example.pricecomparison.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pricecomparison.feature.products.state.ProductsEvent
import com.example.pricecomparison.feature.products.state.ProductsState
import com.example.pricecomparison.feature.products.usecase.FetchProductsUseCase
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

typealias ProductsProcessor = Processor<ProductsEvent, ProductsState, Any>

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val fetchProductsUseCase: FetchProductsUseCase
) : ViewModel() {
    val processor: ProductsProcessor = processor(
        initialState = ProductsState(),
        onEvent = { event ->
            when (event) {
                is ProductsEvent.FetchProducts -> fetchProductsUseCase(
                    categoryId = event.categoryId,
                    coroutineScope = viewModelScope
                )
                ProductsEvent.RefreshProducts -> flowOf()
            }
        }
    )
}
