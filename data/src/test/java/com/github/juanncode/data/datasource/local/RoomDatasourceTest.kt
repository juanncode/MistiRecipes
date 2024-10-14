package com.github.juanncode.data.datasource.local

import com.github.juanncode.data.database.RecipeDao
import com.github.juanncode.data.database.RecipeDatabase
import com.github.juanncode.data.recipeEntityMock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class RoomDatasourceTest {
    @Mock
    private lateinit var recipeDao: RecipeDao
    @Mock
    private lateinit var recipeDatabase: RecipeDatabase
    private lateinit var  roomDatasource : RoomDataSource

    @Before
    fun setUp() {
        roomDatasource = RoomDataSource(recipeDatabase)
    }

    @Test
    fun `test get movies`() = runTest {
        val recipesEntities = listOf(
            recipeEntityMock.copy(id = 1 ,name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil")),
            recipeEntityMock.copy(id = 2, name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil")),
        )
        `when`(recipeDao.getRecipes()).thenReturn(flowOf(recipesEntities))

        `when`(recipeDatabase.recipeDao).thenReturn(recipeDao)

        val recipes = roomDatasource.getRecipes().first()

        assertEquals(2, recipes.size)
        assertEquals("Pasta", recipes[0].name)

        verify(recipeDao).getRecipes()
    }


    @Test
    fun testGetMovieById() = runTest {
        val recipeEntity = recipeEntityMock.copy(id = 1 ,name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil"))
        `when`(recipeDao.getRecipeById(1)).thenReturn(recipeEntity)

        `when`(recipeDatabase.recipeDao).thenReturn(recipeDao)

        val movie = roomDatasource.getRecipeById(1)

        assertEquals("Pasta", movie.name)

        verify(recipeDao).getRecipeById(1)
    }


}