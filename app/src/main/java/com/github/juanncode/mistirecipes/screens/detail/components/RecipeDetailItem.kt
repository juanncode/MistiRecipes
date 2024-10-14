@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.github.juanncode.mistirecipes.screens.detail.components


import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.juanncode.domain.Recipe
import com.github.juanncode.mistirecipes.R
import com.github.juanncode.mistirecipes.helpers.getDifficultyColor
import com.github.juanncode.mistirecipes.ui.theme.MistiBlue
import com.github.juanncode.mistirecipes.ui.theme.MistiGreen
import com.github.juanncode.mistirecipes.ui.theme.MistiRecipesTheme

@Composable
fun RecipeDetailItem(
    recipe: Recipe,

    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope?
) {
    val scrollState = rememberScrollState()
    var expandedIngredientsState by remember { mutableStateOf(false) }
    var expandedPreparationState by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
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
                                Color.Black.copy(alpha = 0.2f),
                                Color.Black.copy(alpha = 0.7f),
                                Color.Black.copy(alpha = 0.9f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                with(sharedTransitionScope) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(recipe.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = recipe.name,
                        modifier = Modifier
                            .height(400.dp)
                            .width(320.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/${recipe.id}"),
                                animatedVisibilityScope = animatedVisibilityScope!!
                            ),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )



                Text(
                    text = "Dificultad: ${recipe.difficulty}",
                    style = MaterialTheme.typography.titleMedium,
                    color = getDifficultyColor(recipe.difficulty),
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "Tiempo preparación: ${recipe.preparationTime}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis
                )
                FilledTonalButton(
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MistiGreen
                    ),
                    onClick = {
                        expandedIngredientsState = !expandedIngredientsState
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(id = R.string.see_ingredients),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = if (!expandedIngredientsState) R.drawable.keyboard_arrow_down else R.drawable.keyboard_arrow_up),
                        contentDescription = stringResource(
                            id = if (expandedIngredientsState) R.string.keyboard_arrow_down else R.string.keyboard_arrow_up

                        ),
                        tint = Color.White
                    )
                }
                if (expandedIngredientsState)
                    recipe.ingredients.forEachIndexed { index, s ->
                        TitleItem(
                            nameStep = stringResource(id = R.string.ingredient),
                            index = index,
                            value = s
                        )
                    }


                FilledTonalButton(
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MistiBlue
                    ),
                    onClick = {
                        expandedPreparationState = !expandedPreparationState
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(id = R.string.see_preparation),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = if (!expandedPreparationState) R.drawable.keyboard_arrow_down else R.drawable.keyboard_arrow_up),
                        contentDescription = stringResource(
                            id = if (expandedPreparationState) R.string.keyboard_arrow_down else R.string.keyboard_arrow_up

                        ),
                        tint = Color.White
                    )
                }
                if (expandedPreparationState)
                    recipe.preparation.forEachIndexed { index, s ->
                        TitleItem(
                            nameStep = stringResource(id = R.string.step),
                            index = index,
                            value = s
                        )
                    }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeDetailItemPreview() {
    MistiRecipesTheme {
        SharedTransitionScope {
            RecipeDetailItem(
                recipe = Recipe(
                    id = 1,
                    difficulty = "Easy",
                    name = "Pastel de papa",
                    preparationTime = "12 min",
                    ingredients = mutableListOf(),
                    preparation = mutableListOf(),
                    image = ""
                ),
                sharedTransitionScope = this,
                animatedVisibilityScope = null
            )
        }

    }
}