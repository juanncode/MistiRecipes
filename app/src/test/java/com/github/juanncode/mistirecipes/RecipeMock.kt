package com.github.juanncode.mistirecipes

import com.github.juanncode.domain.Recipe

val recipeMock =  Recipe(
    id = 1,
    difficulty = "Easy",
    name = "Pastel de papa",
    preparationTime = "12 min",
    ingredients = mutableListOf("Papa", "Tomate", "Queso"),
    preparation = mutableListOf("Paso 1", "Paso 2", "Paso 3"),
    image = "example.test/image2.png"
)