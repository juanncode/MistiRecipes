package com.github.juanncode.mistirecipes.screens.detail

sealed interface DetailEvent {
    data class GetRecipe(val id: Int): DetailEvent

}