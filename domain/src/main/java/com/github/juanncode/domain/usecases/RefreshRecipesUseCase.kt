package com.github.juanncode.domain.usecases

import com.github.juanncode.domain.repository.RecipeRepository
import com.github.juanncode.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RefreshRecipesUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            repository.refreshRecipes()
        }

    }
}