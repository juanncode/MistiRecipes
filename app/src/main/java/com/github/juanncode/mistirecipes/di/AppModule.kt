package com.github.juanncode.mistirecipes.di

import com.github.juanncode.mistirecipes.screens.detail.DetailViewModel
import com.github.juanncode.mistirecipes.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}