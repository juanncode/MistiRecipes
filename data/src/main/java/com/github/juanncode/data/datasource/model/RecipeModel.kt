package com.github.juanncode.data.datasource.model

import com.google.gson.annotations.SerializedName

data class RecipeModel(
    val difficulty: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val name: String,
    val preparation: List<String>,
    @SerializedName("preparation_time")
    val preparationTime: String
)
