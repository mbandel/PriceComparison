package com.example.pricecomparison.feature.products.state

sealed interface ProductsEvent {
    object RefreshProducts : ProductsEvent
    data class FetchProducts(val categoryId: Int) : ProductsEvent
}
