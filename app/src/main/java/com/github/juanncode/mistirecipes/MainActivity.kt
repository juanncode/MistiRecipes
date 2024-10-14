@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.github.juanncode.mistirecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.github.juanncode.mistirecipes.config.AppRouter
import com.github.juanncode.mistirecipes.screens.detail.DetailScreen
import com.github.juanncode.mistirecipes.screens.detail.DetailViewModel
import com.github.juanncode.mistirecipes.screens.home.HomeScreen
import com.github.juanncode.mistirecipes.screens.home.HomeViewModel
import com.github.juanncode.mistirecipes.ui.theme.MistiRecipesTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MistiRecipesTheme {
                Surface {

                    SharedTransitionLayout {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = AppRouter.HomeRoute,

                            ) {
                            composable<AppRouter.HomeRoute> {
                                val viewModel = koinViewModel<HomeViewModel>()
                                val state = viewModel.state.collectAsState().value

                                HomeScreen(
                                    state = state,
                                    onEvent = viewModel::onEvent,
                                    navController = navController,
                                    animatedVisibilityScope = this
                                )
                            }

                            composable<AppRouter.DetailRoute> {
                                val args = it.toRoute<AppRouter.DetailRoute>()
                                val viewModel = koinViewModel<DetailViewModel>()
                                val state = viewModel.state.collectAsState().value
                                DetailScreen(
                                    idRecipe = args.idRecipe,
                                    state = state,
                                    navController = navController,
                                    onEvent = {
                                        viewModel.onEvent(it)
                                    },
                                    animatedVisibilityScope = this
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}
