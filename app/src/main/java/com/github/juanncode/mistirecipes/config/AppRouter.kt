package com.github.juanncode.mistirecipes.config

import kotlinx.serialization.Serializable

sealed class AppRouter {

    @Serializable
    data object HomeRoute: AppRouter()
    @Serializable
    data class DetailRoute(val idMovie: Long) : AppRouter()
}