package com.github.juanncode.mistirecipes.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.juanncode.domain.repository.RecipeRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipeRepository: RecipeRepository
): ViewModel() {

    fun getRecipes() {
        viewModelScope.launch {
            recipeRepository.getRecipes()
        }

    }

}