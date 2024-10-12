package com.github.juanncode.data.datasource.remote

import com.github.juanncode.data.datasource.remote.model.RecipeModel
import com.github.juanncode.data.retrofit.ApiService
import com.github.juanncode.data.util.safeApiCall
import com.github.juanncode.domain.utils.Resource

class RetrofitDataSource(
    private val apiService: ApiService
): RemoteDataSource {
    override suspend fun getRecipes(): Resource<List<RecipeModel>> {
        return  safeApiCall {
            apiService.fetchRecipes()
        }
    }

}