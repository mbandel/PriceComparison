package com.example.pricecomparison.feature.categories

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pricecomparison.R
import com.example.pricecomparison.feature.categories.data.Category
import com.example.pricecomparison.feature.categories.state.CategoriesEvent
import com.tomcz.ellipse.common.collectAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesScreen() {
    val processor = viewModel<CategoriesViewModel>().processor
    val categories by processor.collectAsState { it.categories }
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 3)
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category,
                onClick = {
                    processor.sendEvent(CategoriesEvent.OnItemClick(categoryId = category.id))
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CategoryItem(category: Category, onClick: () -> Unit) {
    Card(
        onClick = onClick,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = getCategoryImage(name = category.name),
                contentDescription = null
            )
            Text(text = category.name)
        }
    }
}

@Composable
private fun getCategoryImage(name: String): Painter {
    return when (name) {
        "owoce" -> painterResource(id = R.drawable.ic_fruit)
        else -> painterResource(id = R.drawable.ic_bread)
    }
}
