package com.github.juanncode.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Upsert
    suspend fun upsertAll(beers: List<RecipeEntity>)

    @Query("SELECT * FROM recipeentity")
    fun getRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT COUNT(*) FROM recipeentity")
    fun getQuantityRecipes(): Int

    @Query("SELECT * FROM recipeentity where id = :id")
    fun getRecipeById(id: Int): RecipeEntity

    @Query("DELETE FROM recipeentity")
    suspend fun clearAll()
}