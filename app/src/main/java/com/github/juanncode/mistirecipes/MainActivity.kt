@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.github.juanncode.mistirecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.juanncode.mistirecipes.config.AppRouter
import com.github.juanncode.mistirecipes.screens.HomeScreen
import com.github.juanncode.mistirecipes.screens.HomeViewModel
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
                                viewModel.getRecipes()
                                HomeScreen()
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MistiRecipesTheme {
        Greeting("Android")
    }
}