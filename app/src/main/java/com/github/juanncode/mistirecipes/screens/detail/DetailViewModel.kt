package com.github.juanncode.mistirecipes.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.juanncode.domain.usecases.GetRecipeByIdUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
): ViewModel() {
    var state by mutableStateOf(DetailState())
        private set

    private fun getMovie(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val recipe = getRecipeByIdUseCase(id)
            state = state.copy(isLoading = false, recipe = recipe)
        }

    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetMovie -> getMovie(event.id)
        }

    }


}