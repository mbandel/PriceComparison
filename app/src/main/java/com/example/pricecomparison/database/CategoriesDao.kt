package com.example.pricecomparison.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.pricecomparison.model.Category
import com.example.pricecomparison.util.Constants.CATEGORY_TABLE

@Dao
interface CategoriesDao {
    @Query("SELECT * FROM $CATEGORY_TABLE")
    fun getAllCategories(): List<Category>

    @Query("SELECT * FROM $CATEGORY_TABLE WHERE id = :id")
    fun getCategoryById(id: Int): Category

    @Insert(onConflict = REPLACE)
    fun saveAllCategories(categories: List<Category>)
}
