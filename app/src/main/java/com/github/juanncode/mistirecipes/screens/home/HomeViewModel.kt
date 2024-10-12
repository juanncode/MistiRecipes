package com.github.juanncode.mistirecipes.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.juanncode.domain.repository.RecipeRepository
import com.github.juanncode.domain.usecases.FetchRecipesUseCase
import com.github.juanncode.domain.usecases.GetRecipesFlowUseCase
import com.github.juanncode.domain.usecases.IsRecipesEmptyUseCase
import com.github.juanncode.domain.usecases.RefreshRecipesUseCase
import com.github.juanncode.domain.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val isRecipesEmptyUseCase: IsRecipesEmptyUseCase,
    private val refreshRecipesUseCase: RefreshRecipesUseCase,
    private val getRecipesFlowUseCase: GetRecipesFlowUseCase,
    private val fetchRecipesUseCase: FetchRecipesUseCase
): ViewModel() {

    private var _state =  MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when(event) {
            HomeEvent.CleanError -> _state.value = _state.value.copy(error = null)
            HomeEvent.InitialValues -> {
                getRecipesFlow()
                fetchRecipes()
            }
            HomeEvent.RefreshMovies -> refreshRecipes()
        }
    }

    fun getRecipesFlow() {
       getRecipesFlowUseCase().onEach {
            _state.value = _state.value.copy(recipes = it)
        }.launchIn(viewModelScope)
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            delay(500)
            val response = fetchRecipesUseCase()
            _state.value = _state.value.copy(loading = false)
            if (response is Resource.Error) {
                _state.value = _state.value.copy(error = response.error)
            }
        }
    }

    fun refreshRecipes() {
        viewModelScope.launch {
            _state.value = _state.value.copy(refreshing = true)
            delay(500)
            val response = refreshRecipesUseCase()
            _state.value = _state.value.copy(refreshing = false)
            if (response is Resource.Error) {
                _state.value = _state.value.copy(error = response.error)
            }
        }
    }
}