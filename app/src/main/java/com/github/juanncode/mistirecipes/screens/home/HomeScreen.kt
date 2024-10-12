@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)

package com.github.juanncode.mistirecipes.screens.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.juanncode.mistirecipes.R
import com.github.juanncode.mistirecipes.config.AppRouter
import com.github.juanncode.mistirecipes.screens.components.GradientBackground
import com.github.juanncode.mistirecipes.screens.components.MistiToolbar
import com.github.juanncode.mistirecipes.screens.home.components.RecipeItem
import com.github.juanncode.mistirecipes.ui.theme.MistiRecipesTheme

private const val columns = 2


@Composable
fun SharedTransitionScope.HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope?
) {

    val context = LocalContext.current
    val listState = rememberLazyGridState()

    LaunchedEffect(key1 = true) {
        if (state.recipes.isEmpty()) onEvent(HomeEvent.InitialValues)
    }

    LaunchedEffect(key1 = state.error) {
        if (state.error != null) {
            Toast.makeText(
                context,
                state.error.userMessage,
                Toast.LENGTH_LONG
            ).show()
            onEvent(HomeEvent.CleanError)

        }
    }

    GradientBackground {
        PullToRefreshBox(
            isRefreshing = state.refreshing,
            onRefresh = {
                onEvent(HomeEvent.RefreshMovies)
            }
        ) {
            LazyVerticalGrid(
                state = listState,
                columns = GridCells.Fixed(count = columns),
                contentPadding = PaddingValues(horizontal = 5.dp, vertical = 10.dp)
            ) {
                item(span = { GridItemSpan(columns) }) {
                    MistiToolbar(
                        title = stringResource(id = R.string.misti_toolbar)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.recipe_logo),
                            contentDescription = "bet"
                        )
                    }
                }
                items(
                    state.recipes
                ) { recipe ->
                    RecipeItem(recipe = recipe, sharedTransitionScope = this@HomeScreen, animatedVisibilityScope = animatedVisibilityScope!!) {
                        navController.navigate(AppRouter.DetailRoute(idRecipe = recipe.id))
                    }
                }
            }

            if (state.loading) {
                Column(
                    modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.loading),
                        style = TextStyle(fontWeight = FontWeight.SemiBold)
                    )
                }
            }

        }
    }


}

@Preview

@Composable

private fun HomeScreenPreview() {

    MistiRecipesTheme{
        SharedTransitionScope {
            HomeScreen(
                state = HomeState(),
                onEvent = {},
                navController = NavController(LocalContext.current),
                animatedVisibilityScope = null,

            )
        }

    }

}