package com.github.juanncode.data.datasource

import com.github.juanncode.domain.Recipe
import com.github.juanncode.domain.utils.Resource

interface RemoteDataSource {
    suspend fun getRecipes(): Resource<List<Recipe>>
}