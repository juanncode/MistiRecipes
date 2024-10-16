@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.juanncode.mistirecipes.screens.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.juanncode.mistirecipes.R
import com.github.juanncode.mistirecipes.ui.theme.ArrowLeftIcon
import com.github.juanncode.mistirecipes.ui.theme.MistiRecipesTheme

@Composable
fun MistiToolbar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit = {},
    showBackButton: Boolean = false,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null

) {
    TopAppBar(
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.White
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                startContent?.invoke()
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    onBackClick()
                }) {
                    Icon(
                        imageVector = ArrowLeftIcon,
                        contentDescription = stringResource(id = R.string.go_back),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },



    )
}

@Preview
@Composable
private fun PreviewCorrerToolbar() {
    MistiRecipesTheme {
        MistiToolbar(title = "Misti toolbar", startContent = {
            Image(
                painter = painterResource(id = R.drawable.recipe_logo),
                contentDescription = "bet"
            )
        })
    }

}