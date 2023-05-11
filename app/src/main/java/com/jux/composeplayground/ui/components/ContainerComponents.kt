package com.jux.composeplayground.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jux.composeplayground.R
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun DefaultPage(content: @Composable () -> Unit) {
    ComposePlaygroundTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultScaffold(
    @StringRes topAppBarTitleResId: Int,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBarWithTitle(
                titleResId = topAppBarTitleResId,
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
                modifier = modifier
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithTitle(
    @StringRes titleResId: Int,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            LargeDisplayWithText(
                resId = titleResId,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (canNavigateBack.not()) return@TopAppBar
            IconButtonWithDrawable(
                resId = R.drawable.outline_arrow_back_24,
                description = stringResource(id = R.string.back_button),
                onClick = navigateUp
            )
        }
    )
}