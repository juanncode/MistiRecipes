@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.github.juanncode.mistirecipes.screens.home.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.juanncode.domain.Recipe

@Composable
fun RecipeItem(
    recipe: Recipe,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope?,
    onClickListener: (Long) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(radius = 3.dp),
                    contentScale = ContentScale.Crop
                )



            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.4f),
                                Color.Black.copy(alpha = 0.9f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                with(sharedTransitionScope) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(recipe.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = recipe.name,
                        modifier = Modifier
                            .height(180.dp)
                            .width(120.dp)
                            .clip(RoundedCornerShape(12.dp))
//                            .sharedElement(
//                                state = rememberSharedContentState(key = "image/${movie.id}"),
//                                animatedVisibilityScope = animatedVisibilityScope!!
//                            )
                        ,
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = recipe.preparationTime,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(text = recipe.difficulty)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MaterialTheme {
        SharedTransitionScope {
            RecipeItem(
                recipe = Recipe(
                    id = 1,
                    difficulty = "Easy",
                    name = "Pastel de papa",
                    preparationTime = "12 min",
                    ingredients = mutableListOf(),
                    preparation = mutableListOf(),
                    image = ""
                ),
                onClickListener = {},
                sharedTransitionScope = this,
                animatedVisibilityScope = null
            )
        }

    }
}