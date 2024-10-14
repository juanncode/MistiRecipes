@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)

package com.github.juanncode.mistirecipes.screens.detail


import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.github.juanncode.mistirecipes.screens.components.GradientBackground
import com.github.juanncode.mistirecipes.screens.components.MistiToolbar
import com.github.juanncode.mistirecipes.screens.detail.components.RecipeDetailItem

@Composable
fun SharedTransitionScope.DetailScreen(
    idRecipe: Int,
    onEvent: (DetailEvent) -> Unit,
    state: DetailState,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    LaunchedEffect(key1 = true) {
        onEvent(DetailEvent.GetRecipe(idRecipe))
    }
    GradientBackground {
        if (state.isLoading)
            CircularProgressIndicator()
        state.recipe?.let {
            MistiToolbar(
                title = it.name,
                showBackButton = true,
                onBackClick = {
                    navController.popBackStack()
                }
            )
            RecipeDetailItem(
                recipe = state.recipe,
                sharedTransitionScope = this@DetailScreen,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }

    }


}