package com.github.juanncode.domain.repository

import com.github.juanncode.domain.Recipe
import com.github.juanncode.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipesFlow(): Flow<List<Recipe>>
    suspend fun fetchRecipes(): Resource<Unit>
    suspend fun refreshRecipes(): Resource<Unit>
    suspend fun isRecipesEmpty(): Boolean
    suspend fun getRecipe(id: Int): Recipe
}