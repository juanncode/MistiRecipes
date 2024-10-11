package com.github.juanncode.data.retrofit

import com.github.juanncode.data.datasource.model.RecipeModel
import retrofit2.http.GET

interface ApiService {
    @GET("recipes")
    suspend fun fetchRecipes(): List<RecipeModel>
}