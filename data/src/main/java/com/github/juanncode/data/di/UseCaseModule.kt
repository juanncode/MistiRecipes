package com.github.juanncode.data.di

import com.github.juanncode.domain.usecases.FetchRecipesUseCase
import com.github.juanncode.domain.usecases.FilterRecipesByQueryUseCase
import com.github.juanncode.domain.usecases.GetRecipeByIdUseCase
import com.github.juanncode.domain.usecases.GetRecipesFlowUseCase
import com.github.juanncode.domain.usecases.IsRecipesEmptyUseCase
import com.github.juanncode.domain.usecases.RefreshRecipesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { IsRecipesEmptyUseCase(get()) }
    single { RefreshRecipesUseCase(get()) }
    single { GetRecipesFlowUseCase(get()) }
    single { FetchRecipesUseCase(get()) }
    single { GetRecipeByIdUseCase(get()) }
    single { FilterRecipesByQueryUseCase() }

}