package com.github.juanncode.data.datasource.local

import androidx.room.withTransaction
import com.github.juanncode.data.database.RecipeDatabase
import com.github.juanncode.data.datasource.remote.model.RecipeModel
import com.github.juanncode.data.mappers.toDatabase
import com.github.juanncode.data.mappers.toDomain
import com.github.juanncode.domain.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDataSource(
    private val recipeDatabase: RecipeDatabase
) : LocalDataSource {
    override fun getRecipes(): Flow<List<Recipe>> {
        return recipeDatabase.recipeDao.getRecipes().map { it.map { it.toDomain() } }
    }

    override suspend fun saveRecipe(recipes: List<RecipeModel>, isRefreshing: Boolean) {
        recipeDatabase.withTransaction {
            if(isRefreshing) {
                recipeDatabase.recipeDao.clearAll()
            }
            recipeDatabase.recipeDao.upsertAll(recipes.map { it.toDatabase() })
        }
    }

    override suspend fun recipesIsEmpty(): Boolean {
        return recipeDatabase.recipeDao.getQuantityRecipes() == 0
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return recipeDatabase.recipeDao.getRecipeById(id).toDomain()
    }
}