package com.jux.composeplayground.ui.tutorials.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme

class CupcakeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                CupcakeOrderFunnel()
            }
        }
    }
}