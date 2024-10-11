package com.github.juanncode.domain

data class Recipe(
    val difficulty: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val name: String,
    val preparation: List<String>,
    val preparationTime: String
)