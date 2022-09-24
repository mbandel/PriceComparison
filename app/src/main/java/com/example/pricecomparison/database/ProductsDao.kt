package com.example.pricecomparison.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.pricecomparison.model.Category
import com.example.pricecomparison.model.Product
import com.example.pricecomparison.util.Constants.PRODUCT_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM $PRODUCT_TABLE WHERE category = :category")
    fun observeProducts(category: Category): Flow<List<Product>>

    @Insert(onConflict = REPLACE)
    fun insertProducts(products: List<Product>)
}
