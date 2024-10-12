package com.github.juanncode.mistirecipes.screens.detail

sealed interface DetailEvent {
    data class GetMovie(val id: Int): DetailEvent

}