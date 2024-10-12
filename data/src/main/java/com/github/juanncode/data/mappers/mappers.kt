package com.github.juanncode.data.mappers

import com.github.juanncode.data.database.RecipeEntity
import com.github.juanncode.data.datasource.remote.model.RecipeModel
import com.github.juanncode.domain.Recipe

fun RecipeModel.toDatabase() = RecipeEntity(
    id = id,
    difficulty = difficulty,
    image = image,
    ingredients = ingredients,
    name = name,
    preparation = preparation,
    preparationTime = preparationTime
)

fun RecipeEntity.toDomain() = Recipe(
    difficulty = difficulty,
    id = id,
    image = image,
    ingredients = ingredients,
    name = name,
    preparation = preparation,
    preparationTime = preparationTime
)

