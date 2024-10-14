package com.github.juanncode.mistirecipes.screens.home

sealed interface HomeEvent {
    data object CleanError: HomeEvent
    data object RefreshRecipes: HomeEvent
    data object InitialValues: HomeEvent
    data object CleanTextField: HomeEvent
}