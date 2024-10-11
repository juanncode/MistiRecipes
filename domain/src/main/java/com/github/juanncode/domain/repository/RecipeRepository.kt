package com.github.juanncode.domain.repository

import com.github.juanncode.domain.Recipe
import com.github.juanncode.domain.utils.Resource

interface RecipeRepository {
    suspend fun getRecipes(): Resource<List<Recipe>>
}