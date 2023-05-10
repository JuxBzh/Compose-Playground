package com.jux.composeplayground.ui.tutorials.unscramble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jux.composeplayground.R
import com.jux.composeplayground.ui.components.DefaultScaffold
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme

class UnscrambleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                DefaultScaffold(topAppBarTitleResId = R.string.unscramble_activity) {
                    GameScreen(it)
                }
            }
        }
    }
}