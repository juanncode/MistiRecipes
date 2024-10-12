package com.github.juanncode.domain.usecases

import com.github.juanncode.domain.Recipe
import com.github.juanncode.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRecipeByIdUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Int): Recipe {
        return withContext(Dispatchers.IO) {repository.getRecipe(id)}
    }
}