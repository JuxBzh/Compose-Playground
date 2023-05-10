package com.jux.composeplayground.ui.tutorials.tip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jux.composeplayground.ui.components.DefaultPage

class TipCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPage {
                TipCalculator()
            }
        }
    }
}