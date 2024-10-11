package com.github.juanncode.mistirecipes.di

import com.github.juanncode.mistirecipes.screens.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
}