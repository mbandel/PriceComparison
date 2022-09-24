package com.example.pricecomparison.feature.products

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.pricecomparison.feature.products.data.ProductItem
import com.example.pricecomparison.feature.products.state.ProductsEvent
import com.example.pricecomparison.model.Product
import com.example.pricecomparison.ui.theme.MediumPadding
import com.tomcz.ellipse.common.collectAsState

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProductsScreen(
    categoryId: Int,
    processor: ProductsProcessor = viewModel<ProductsViewModel>().processor
) {
    processor.sendEvent(ProductsEvent.FetchProducts(categoryId = categoryId))
    val products by processor.collectAsState { it.products }
    val navigator = LocalNavigator.currentOrThrow
    val counter = mutableStateOf(0)

    Column {
        ProductList(
            products = products,
            onClick = { }
        )

        Button(onClick = { counter.value++ }) {
            Text(text = counter.value.toString())
        }
    }
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
