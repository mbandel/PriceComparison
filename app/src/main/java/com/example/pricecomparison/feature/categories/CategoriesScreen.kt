package com.example.pricecomparison.feature.categories

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pricecomparison.R
import com.example.pricecomparison.feature.categories.data.Category
import com.example.pricecomparison.feature.categories.state.CategoriesEvent
import com.example.pricecomparison.ui.theme.MediumPadding
import com.example.pricecomparison.ui.theme.SmallPadding
import com.tomcz.ellipse.common.collectAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesScreen(
    processor: CategoriesProcessor = viewModel<CategoriesViewModel>().processor
) {
    val categories by processor.collectAsState { it.categories }
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 2),
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(SmallPadding),
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
        modifier = Modifier.padding(all = MediumPadding)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = getCategoryImage(name = category.name),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .width(64.dp)
                    .padding(all = SmallPadding)
            )
            Text(
                text = category.name,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
private fun getCategoryImage(name: String): Painter {
    return when (name) {
        "owoce" -> painterResource(id = R.drawable.ic_fruit)
        "warzywa" -> painterResource(id = R.drawable.ic_vegetable)
        "mięso" -> painterResource(id = R.drawable.ic_meat)
        "ryby" -> painterResource(id = R.drawable.ic_fish)
        "alkohole" -> painterResource(id = R.drawable.ic_alcohol)
        "nabiał" -> painterResource(id = R.drawable.ic_milk)
        "słodycze" -> painterResource(id = R.drawable.ic_candy)
        "napoje" -> painterResource(id = R.drawable.ic_drinks)
        "przyprawy" -> painterResource(id = R.drawable.spices)
        else -> painterResource(id = R.drawable.ic_bread)
    }
}
