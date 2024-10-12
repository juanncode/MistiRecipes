package com.github.juanncode.data.di

import androidx.room.Room
import com.github.juanncode.data.database.RecipeDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            RecipeDatabase::class.java,
            "recipe.db"
        ).build()
    }

    single {
        get<RecipeDatabase>().recipeDao
    }
}