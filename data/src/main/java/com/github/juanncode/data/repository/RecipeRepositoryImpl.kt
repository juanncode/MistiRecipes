package com.github.juanncode.data.repository

import com.github.juanncode.data.datasource.local.LocalDataSource
import com.github.juanncode.data.datasource.remote.RemoteDataSource
import com.github.juanncode.domain.Recipe
import com.github.juanncode.domain.repository.RecipeRepository
import com.github.juanncode.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RecipeRepository{
    override fun getRecipesFlow(): Flow<List<Recipe>> {
        return localDataSource.getRecipes()
    }

    override suspend fun fetchRecipes(): Resource<Unit> {
        val response = remoteDataSource.getRecipes()
        return when(response) {
            is Resource.Error -> Resource.Error(response.error)
            is Resource.Success -> {
                localDataSource.saveRecipe(response.value, isRefreshing = false)
                Resource.Success(Unit)
            }
        }
    }

    override suspend fun refreshRecipes(): Resource<Unit> {
        val response = remoteDataSource.getRecipes()
        return when(response) {
            is Resource.Error -> Resource.Error(response.error)
            is Resource.Success -> {
                localDataSource.saveRecipe(response.value, isRefreshing = true)
                Resource.Success(Unit)
            }
        }
    }

    override suspend fun isRecipesEmpty(): Boolean {
        return localDataSource.recipesIsEmpty()
    }

    override suspend fun getRecipe(id: Int): Recipe {
        return localDataSource.getRecipeById(id)
    }

}