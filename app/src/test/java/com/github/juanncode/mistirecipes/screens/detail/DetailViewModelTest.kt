@file:OptIn(ExperimentalCoroutinesApi::class)

package com.github.juanncode.mistirecipes.screens.detail

import androidx.compose.runtime.snapshotFlow
import app.cash.turbine.test
import com.github.juanncode.domain.usecases.GetRecipeByIdUseCase
import com.github.juanncode.mistirecipes.CoroutinesTestRule
import com.github.juanncode.mistirecipes.screens.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var getRecipeByIdUseCase: GetRecipeByIdUseCase

    @get:Rule
    val rule = CoroutinesTestRule()

    @Before
    fun setup() {
        viewModel = DetailViewModel(
            getRecipeByIdUseCase
        )
    }

    @Test
    fun `get recipes sets loading to true, fetches recipe, and sets loading to false`() = runTest {
        val mockRecipe = com.github.juanncode.mistirecipes.recipeMock.copy(id = 1, name = "Recipe 1", ingredients = listOf("Tomato", "Pasta"))
        `when`(getRecipeByIdUseCase(1)).thenReturn(mockRecipe)

        viewModel.onEvent(DetailEvent.GetMovie(1))

        viewModel.state.test {
            awaitItem()
            val firstEmission = awaitItem()
            val secondEmission = awaitItem()
            assertTrue(firstEmission.isLoading)
            assertFalse(secondEmission.isLoading)
            assertEquals(mockRecipe, secondEmission.recipe)
        }
    }

}