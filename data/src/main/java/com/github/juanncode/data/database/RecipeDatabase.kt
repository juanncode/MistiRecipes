package com.github.juanncode.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [RecipeEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase(){
    abstract val recipeDao: RecipeDao
}