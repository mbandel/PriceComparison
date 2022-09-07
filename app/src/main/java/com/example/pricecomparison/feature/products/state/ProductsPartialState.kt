package com.example.pricecomparison.feature.products.state

import com.example.pricecomparison.model.Product
import com.tomcz.ellipse.PartialState

sealed interface ProductsPartialState : PartialState<ProductsState> {

    data class UpdateProducts(val products: List<Product>) : ProductsPartialState {
        override fun reduce(oldState: ProductsState): ProductsState =
            oldState.copy(products = products)
    }
}