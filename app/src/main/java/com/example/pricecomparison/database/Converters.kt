package com.example.pricecomparison.database

import androidx.room.TypeConverter
import com.example.pricecomparison.model.Category
import com.example.pricecomparison.model.Product
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val categoryAdapter = moshi.adapter(Category::class.java)
    private val productAdapter = moshi.adapter(Product::class.java)

    @TypeConverter
    fun fromCategory(value: Category): String =
        categoryAdapter.toJson(value)

    @TypeConverter
    fun toCategory(value: String): Category =
        categoryAdapter.fromJson(value)!!

    @TypeConverter
    fun fromProduct(value: Product): String =
        productAdapter.toJson(value)

    @TypeConverter
    fun toProduct(value: String): Product =
        productAdapter.fromJson(value)!!
}
