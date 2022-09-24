package com.example.pricecomparison.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pricecomparison.util.Constants.CATEGORY_TABLE

@Entity(tableName = CATEGORY_TABLE)
data class Category(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)
