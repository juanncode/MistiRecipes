package com.github.juanncode.mistirecipes.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.juanncode.domain.usecases.GetRecipeByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
): ViewModel() {
    private var _state =  MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    private fun getMovie(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val recipe = getRecipeByIdUseCase(id)
            _state.value = _state.value.copy(isLoading = false, recipe = recipe)
        }

    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetMovie -> getMovie(event.id)
        }

    }


}