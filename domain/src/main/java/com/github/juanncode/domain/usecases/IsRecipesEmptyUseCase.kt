package com.github.juanncode.domain.usecases

import com.github.juanncode.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IsRecipesEmptyUseCase(
    private val repository: RecipeRepository
) {

    suspend operator fun invoke(): Boolean {
        return withContext(Dispatchers.IO) {repository.isRecipesEmpty()}
    }
}