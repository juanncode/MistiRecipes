package com.github.juanncode.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity (
    @PrimaryKey
    val id: Int,
    val difficulty: String,
    val image: String,
    val ingredients: List<String>,
    val name: String,
    val preparation: List<String>,
    val preparationTime: String
)