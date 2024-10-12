package com.github.juanncode.data.datasource.local

import com.github.juanncode.data.datasource.remote.model.RecipeModel
import com.github.juanncode.domain.Recipe
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun saveRecipe(recipes: List<RecipeModel>, isRefreshing: Boolean)
    suspend fun recipesIsEmpty(): Boolean
    suspend fun getRecipeById(id: Int): Recipe
}