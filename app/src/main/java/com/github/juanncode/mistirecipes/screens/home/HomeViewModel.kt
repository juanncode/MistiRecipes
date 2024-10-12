package com.github.juanncode.mistirecipes.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.juanncode.domain.repository.RecipeRepository
import com.github.juanncode.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipeRepository: RecipeRepository
): ViewModel() {

    private var _state =  MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when(event) {
            HomeEvent.CleanError -> _state.value = _state.value.copy(error = null)
            HomeEvent.InitialValues -> getRecipes()
            HomeEvent.RefreshMovies -> getRecipes()
        }
    }

    fun getRecipes() {

        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val response = recipeRepository.getRecipes()
            _state.value = _state.value.copy(loading = false)

            when(response) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = response.error)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(recipes = response.value)
                }
            }
        }

    }

}