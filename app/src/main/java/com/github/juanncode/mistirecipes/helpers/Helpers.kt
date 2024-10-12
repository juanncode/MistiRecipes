package com.github.juanncode.mistirecipes.helpers

import androidx.compose.ui.graphics.Color

fun getDifficultyColor(difficulty: String): Color {
    return when {
        difficulty.contains("Fácil", ignoreCase = true) -> Color.Green
        difficulty.contains("Media", ignoreCase = true) -> Color.Yellow
        difficulty.contains("Difícil", ignoreCase = true) -> Color.Red
        else -> Color.Yellow
    }
}