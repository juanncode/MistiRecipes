package com.github.juanncode.mistirecipes.screens.home

import com.github.juanncode.domain.Recipe
import com.github.juanncode.domain.utils.ErrorGeneric

data class HomeState(
    val loading: Boolean = false,
    val error: ErrorGeneric? = null,
    val recipes: List<Recipe> = emptyList(),
)