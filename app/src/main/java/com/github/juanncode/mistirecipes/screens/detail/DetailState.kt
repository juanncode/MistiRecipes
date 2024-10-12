package com.github.juanncode.mistirecipes.screens.detail

import com.github.juanncode.domain.Recipe


data class DetailState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = false
)