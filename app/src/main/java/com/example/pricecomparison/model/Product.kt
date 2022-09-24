package com.example.pricecomparison.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pricecomparison.util.Constants.PRODUCT_TABLE

@Entity(tableName = PRODUCT_TABLE)
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val category: Category,
    val name: String
)
