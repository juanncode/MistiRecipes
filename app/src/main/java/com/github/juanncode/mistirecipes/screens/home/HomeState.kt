package com.github.juanncode.mistirecipes.screens.home

import androidx.compose.foundation.text.input.TextFieldState
import com.github.juanncode.domain.Recipe
import com.github.juanncode.domain.utils.ErrorGeneric

data class HomeState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val error: ErrorGeneric? = null,
    val textFieldState: TextFieldState = TextFieldState(),
    val recipes: List<Recipe> = emptyList(),
    val recipesBackup: List<Recipe> = emptyList(),
)