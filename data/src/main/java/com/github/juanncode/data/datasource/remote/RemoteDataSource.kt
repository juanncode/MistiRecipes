package com.github.juanncode.data.datasource.remote

import com.github.juanncode.data.datasource.remote.model.RecipeModel
import com.github.juanncode.domain.utils.Resource

interface RemoteDataSource {
    suspend fun getRecipes(): Resource<List<RecipeModel>>
}