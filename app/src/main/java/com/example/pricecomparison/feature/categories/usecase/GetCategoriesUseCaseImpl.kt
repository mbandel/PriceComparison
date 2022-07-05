package com.example.pricecomparison.feature.categories.usecase

import com.example.pricecomparison.feature.categories.CategoriesRepository
import com.example.pricecomparison.feature.categories.data.CategoriesStatus
import com.example.pricecomparison.feature.categories.state.CategoriesPartialState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetCategoriesUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : GetCategoriesUseCase {

    override fun invoke(): Flow<CategoriesPartialState> =
        repository.getCategories()
            .map { categoriesStatus ->
                when (categoriesStatus) {
                    is CategoriesStatus.Loading -> CategoriesPartialState.se
                }
            }

}
