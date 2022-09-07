package com.example.pricecomparison.feature.products

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.pricecomparison.feature.products.state.ProductsEvent
import com.example.pricecomparison.model.Product
import com.example.pricecomparison.ui.theme.MediumPadding
import com.tomcz.ellipse.common.collectAsState

@Composable
fun ProductsScreen(
    categoryId: Int,
    processor: ProductsProcessor = viewModel<ProductsViewModel>().processor
) {
    processor.sendEvent(ProductsEvent.FetchProducts(categoryId = categoryId))
    val products by processor.collectAsState { it.products }
    val navigator = LocalNavigator.currentOrThrow

    ProductList(
        products = products,
        onClick = { }
    )
}

@Composable
private fun ProductList(
    products: List<Product>,
    onClick: (product: Product) -> Unit
) {
    LazyColumn {
        items(products) { product ->
            ProductItem(
                product = product,
                onClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ProductItem(
    product: Product,
    onClick: (product: Product) -> Unit
) {
    Card(
        modifier = Modifier.padding(MediumPadding),
        onClick = { onClick(product) }
    ) {
        Text(text = product.name)
    }
}
