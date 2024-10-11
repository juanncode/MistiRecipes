package com.github.juanncode.data.datasource

import com.github.juanncode.data.datasource.model.RecipeModel
import com.github.juanncode.domain.utils.Resource

interface RemoteDataSource {
    suspend fun getRecipes(): Resource<List<RecipeModel>>
}