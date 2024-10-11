package com.github.juanncode.data.mappers

import com.github.juanncode.data.datasource.model.RecipeModel
import com.github.juanncode.domain.Recipe

fun RecipeModel.toDomain() = Recipe(
    difficulty, id, image, ingredients, name, preparation, preparationTime
)