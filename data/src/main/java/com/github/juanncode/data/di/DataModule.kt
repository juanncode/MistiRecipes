package com.github.juanncode.data.di

import com.github.juanncode.data.datasource.local.LocalDataSource
import com.github.juanncode.data.datasource.local.RoomDataSource
import com.github.juanncode.data.datasource.remote.RemoteDataSource
import com.github.juanncode.data.datasource.remote.RetrofitDataSource
import com.github.juanncode.data.repository.RecipeRepositoryImpl
import com.github.juanncode.domain.repository.RecipeRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module{
    singleOf(::RoomDataSource).bind<LocalDataSource>()
    singleOf(::RetrofitDataSource).bind<RemoteDataSource>()
    singleOf(::RecipeRepositoryImpl).bind<RecipeRepository>()
}