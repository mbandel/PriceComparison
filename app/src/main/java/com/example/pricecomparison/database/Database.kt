package com.example.pricecomparison.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pricecomparison.model.Category
import com.example.pricecomparison.model.Product

@Database(
    entities = [Category::class, Product::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun getCategoriesDao(): CategoriesDao
    abstract fun getProductsDao(): ProductsDao
}
