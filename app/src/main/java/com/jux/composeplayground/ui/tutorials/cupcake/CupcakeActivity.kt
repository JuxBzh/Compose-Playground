package com.jux.composeplayground.ui.tutorials.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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

@Preview
@Composable
fun CupcakeFunnelPreview() {
    ComposePlaygroundTheme {
        CupcakeOrderFunnel()
    }
}