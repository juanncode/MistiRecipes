package com.github.juanncode.domain.usecases

import com.github.juanncode.domain.recipeMock
import org.junit.Assert.*
import org.junit.Test

class FilterRecipesByQueryUseCaseTest{

    private val filterRecipesByQueryUseCase = FilterRecipesByQueryUseCase()

    @Test
    fun `returns all recipes when query is empty`() {
        val recipes = listOf(recipeMock)

        val result = filterRecipesByQueryUseCase("", recipes)

        assertEquals(recipes, result)
    }

    @Test
    fun `filters recipes by name containing query`() {
        val recipes = listOf(
            recipeMock.copy(name = "Pasta", ingredients =  listOf("Tomato", "Pasta", "Basil")),
            recipeMock.copy(name = "Saltado", ingredients =  listOf("Tomato2", "Pasta", "Basil")),
        )

        val result = filterRecipesByQueryUseCase("sal", recipes)

        assertEquals(1, result.size)
        assertEquals("Saltado", result[0].name)
    }

    @Test
    fun `filters recipes by ingredient containing query`() {
        val recipes = listOf(
            recipeMock.copy(name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil")),
            recipeMock.copy(name = "Pasta2", ingredients =  listOf("Cebolla", "Pasta", "Basil")),
            recipeMock.copy(name = "Pasta3", ingredients =  listOf("Pepino", "Pasta", "Basil")),


        )

        val result = filterRecipesByQueryUseCase("Tomate", recipes)

        assertEquals(1, result.size)
        assertEquals("Pasta", result[0].name)
    }

    @Test
    fun `returns an empty list when no recipe matches the query`() {
        val recipes = listOf(
            recipeMock.copy(name = "Pasta", ingredients =  listOf("Tomate", "Pasta", "Basil")),
            recipeMock.copy(name = "Pasta2", ingredients =  listOf("Cebolla", "Pasta", "Basil")),
            recipeMock.copy(name = "Pasta3", ingredients =  listOf("Pepino", "Pasta", "Basil")),
        )

        val result = filterRecipesByQueryUseCase("Pizza", recipes)

        assertEquals(0, result.size)
    }


}