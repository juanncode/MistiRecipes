package com.github.juanncode.mistirecipes.screens.home

import app.cash.turbine.test
import com.github.juanncode.domain.usecases.FetchRecipesUseCase
import com.github.juanncode.domain.usecases.FilterRecipesByQueryUseCase
import com.github.juanncode.domain.usecases.GetRecipesFlowUseCase
import com.github.juanncode.domain.usecases.RefreshRecipesUseCase
import com.github.juanncode.domain.utils.ErrorGeneric
import com.github.juanncode.domain.utils.Resource
import com.github.juanncode.mistirecipes.CoroutinesTestRule
import com.github.juanncode.mistirecipes.recipeMock
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    @Mock
    private lateinit var filterRecipesByQueryUseCase: FilterRecipesByQueryUseCase
    @Mock
    private lateinit var refreshRecipesUseCase: RefreshRecipesUseCase
    @Mock
    private lateinit var getRecipesFlowUseCase: GetRecipesFlowUseCase
    @Mock
    private lateinit var fetchRecipesUseCase: FetchRecipesUseCase

    @get:Rule
    val rule = CoroutinesTestRule()

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            filterRecipesByQueryUseCase, refreshRecipesUseCase, getRecipesFlowUseCase, fetchRecipesUseCase
        )
    }

    @Test
    fun `should update state when getRecipesFlow is called`() = runTest {
        val recipes = listOf(
            recipeMock.copy(id = 1, name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil")),
        )
        `when`(getRecipesFlowUseCase()).thenReturn(flowOf(recipes))

        viewModel.getRecipesFlow()

        viewModel.state.test {
            awaitItem()
            val emission = awaitItem()
            println(emission)
            Assert.assertEquals(recipes,emission.recipes)
        }
    }

    @Test
    fun `should set loading and fetch recipes on event GetNewMovies`() = runTest {
        `when`(fetchRecipesUseCase()).thenReturn(Resource.Success(Unit))

        viewModel.fetchRecipes()

        viewModel.state.test {
            awaitItem()
            val initialEmission = awaitItem()
            assertTrue(initialEmission.loading)


            val finalEmission = awaitItem()
            assertFalse(finalEmission.loading)
        }
    }

    @Test
    fun `should handle error state when fetch recipes returns Resource Error`() = runTest {
        val errorGeneric =  ErrorGeneric(
            code = 0,
            userMessage = "Tuvimos un problema de conectividad, por favor revise su conexión a internet y vuelva a intentarlo",
            error = "Network error"
        )
        `when`(fetchRecipesUseCase()).thenReturn(Resource.Error(errorGeneric))

        viewModel.fetchRecipes()

        viewModel.state.test {
            awaitItem()
            val initialEmission = awaitItem()
            assertTrue(initialEmission.loading)


            val finalEmission = awaitItem()
            assertFalse(finalEmission.loading)
            assertEquals(errorGeneric,awaitItem().error)
        }
    }

    @Test
    fun `refresh recipes sets loading and no error on successful fetch`() = runTest {
        `when`( refreshRecipesUseCase()).thenReturn( Resource.Success(Unit))

        viewModel.onEvent(HomeEvent.RefreshMovies)

        viewModel.state.test {
            awaitItem()
            assertTrue(awaitItem().refreshing)
            assertFalse(awaitItem().refreshing)
        }
    }
}