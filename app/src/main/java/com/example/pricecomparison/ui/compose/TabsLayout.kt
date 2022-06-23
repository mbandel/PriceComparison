package com.example.pricecomparison.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pricecomparison.navigation.TabItem
import com.example.pricecomparison.ui.theme.Teal200
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun TabsLayout() {
    val pagerState = rememberPagerState()
    val screens = listOf(TabItem.Login, TabItem.Register)
    val scope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,
            contentColor = Teal200
        ) {
            screens.forEachIndexed { index, screen ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                ) {
                    Text(
                        text = screen.title,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
        HorizontalPager(
            count = screens.size,
            state = pagerState,
        ) { page ->
            screens[page].view()
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
private fun TabsLayoutPreview() {
    TabsLayout()
}
