package com.jux.composeplayground.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun AppBarWithTitle(@StringRes titleResId: Int, modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        title = { LargeDisplayWithText(resId = titleResId) }
    )
}