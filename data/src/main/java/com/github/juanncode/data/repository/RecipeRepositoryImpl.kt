package com.github.juanncode.data.repository

import com.github.juanncode.data.datasource.RemoteDataSource
import com.github.juanncode.domain.Recipe
import com.github.juanncode.domain.repository.RecipeRepository
import com.github.juanncode.domain.utils.Resource

class RecipeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : RecipeRepository{
    override suspend fun getRecipes(): Resource<List<Recipe>> {
        return remoteDataSource.getRecipes()
    }
}