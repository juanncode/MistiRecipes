package com.github.juanncode.domain.usecases

import com.github.juanncode.domain.repository.RecipeRepository

class GetRecipesFlowUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke() = repository.getRecipesFlow()
}