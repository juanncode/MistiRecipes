package com.github.juanncode.data.repository

import com.github.juanncode.data.datasource.local.LocalDataSource
import com.github.juanncode.data.datasource.remote.RemoteDataSource
import com.github.juanncode.data.mappers.toDomain
import com.github.juanncode.data.recipeEntityMock
import com.github.juanncode.data.recipeRemoteMock
import com.github.juanncode.domain.utils.ErrorGeneric
import com.github.juanncode.domain.utils.Resource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyBoolean
import org.mockito.Mockito.anyList
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class RecipeRepositoryImplTest {

    @Mock
    private lateinit var localDatasource: LocalDataSource
    @Mock
    private lateinit var remoteDatasource: RemoteDataSource
    private lateinit var repository: RecipeRepositoryImpl

    @Before
    fun setup() {
        repository = RecipeRepositoryImpl(localDatasource, remoteDatasource)
    }

    @Test
    fun `fetchRecipes success`() = runTest {
        val recipes = listOf(
            recipeRemoteMock.copy(name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil")),
            recipeRemoteMock.copy(name = "Pasta2", ingredients =  listOf("Cebolla", "Pasta", "Basil")),
            recipeRemoteMock.copy(name = "Pasta3", ingredients =  listOf("Pepino", "Pasta", "Basil")),
        )
        `when`(remoteDatasource.getRecipes()).thenReturn(Resource.Success(recipes))

        val result = repository.fetchRecipes()

        assertTrue(result is Resource.Success)
        verify(localDatasource).saveRecipe(recipes, isRefreshing = false)

    }

    @Test
    fun `fetchRecipes error`() = runTest {
        val error = ErrorGeneric(code = 404, userMessage = "Not Found", error = "error details")
        `when`(remoteDatasource.getRecipes()).thenReturn(Resource.Error(error))

        val result = repository.fetchRecipes()

        assertTrue(result is Resource.Error)
        assertEquals(error, (result as Resource.Error).error)
        verify(localDatasource, never()).saveRecipe(anyList(), anyBoolean())
    }

    @Test
    fun `refreshRecipes success`() = runTest {
        val recipes = listOf(
            recipeRemoteMock.copy(name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil")),
        )
        `when`(remoteDatasource.getRecipes()).thenReturn(Resource.Success(recipes))

        val result = repository.refreshRecipes()

        assertTrue(result is Resource.Success)
        verify(localDatasource).saveRecipe(recipes, isRefreshing = true)
    }

    @Test
    fun `refreshRecipes error`() = runTest {
        val error = ErrorGeneric(code = 500, userMessage = "Server Error", error = "details")
        `when`(remoteDatasource.getRecipes()).thenReturn(Resource.Error(error))

        val result = repository.refreshRecipes()

        assertTrue(result is Resource.Error)
        assertEquals(error, (result as Resource.Error).error)
        verify(localDatasource, never()).saveRecipe(anyList(), anyBoolean())
    }

    @Test
    fun `getRecipesFlow success`() = runTest {
        val recipes = listOf(
            recipeEntityMock.copy(name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil")).toDomain())
        `when`(localDatasource.getRecipes()).thenReturn(flowOf(recipes))

        val result = repository.getRecipesFlow()

        result.collect { recipesList ->
            assertEquals(recipes, recipesList)
        }
    }

    @Test
    fun `isRecipesEmpty returns true`() = runTest {
        `when`(localDatasource.recipesIsEmpty()).thenReturn(true)

        val result = repository.isRecipesEmpty()

        assertTrue(result)
    }

    @Test
    fun `isRecipesEmpty returns false`() = runTest {
        `when`(localDatasource.recipesIsEmpty()).thenReturn(false)

        val result = repository.isRecipesEmpty()

        assertFalse(result)
    }

    @Test
    fun `get Recipes by id`() = runTest {
        val recipes = recipeEntityMock.copy(name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil")).toDomain()

        `when`(localDatasource.getRecipeById(1)).thenReturn(recipes)

        val response = repository.getRecipe(1)

        assertEquals(recipes, response)

    }
}