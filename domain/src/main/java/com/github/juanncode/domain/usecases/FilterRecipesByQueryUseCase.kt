package com.github.juanncode.domain.usecases

import com.github.juanncode.domain.Recipe

class FilterRecipesByQueryUseCase {
    operator fun invoke(query: CharSequence, recipes: List<Recipe>): List<Recipe> {
        if (query.isEmpty()) {
            return recipes
        }
        return recipes.filter { rec ->
            rec.name.lowercase().contains(query.toString().lowercase().trim()) ||
            rec.ingredients.any {
                it.lowercase().contains(query.toString().lowercase().trim())
            }
        }

    }
}