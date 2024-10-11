package com.github.juanncode.data.datasource

import com.github.juanncode.data.mappers.toDomain
import com.github.juanncode.data.retrofit.ApiService
import com.github.juanncode.data.util.safeApiCall
import com.github.juanncode.domain.Recipe
import com.github.juanncode.domain.utils.Resource

class RetrofitDataSource(
    private val apiService: ApiService
): RemoteDataSource {
    override suspend fun getRecipes(): Resource<List<Recipe>> {
        return  safeApiCall {
            apiService.fetchRecipes().map { it.toDomain() }
        }
    }

}