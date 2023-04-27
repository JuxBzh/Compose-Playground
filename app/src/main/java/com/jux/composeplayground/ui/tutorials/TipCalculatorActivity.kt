package com.jux.composeplayground.ui.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jux.composeplayground.R
import com.jux.composeplayground.ui.components.EditNumberField
import com.jux.composeplayground.ui.components.LargeTitleWithText
import com.jux.composeplayground.ui.components.SmallTitleWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme
import java.text.NumberFormat

class TipCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                TipCalculator()
            }
        }
    }
}

@Composable
fun TipCalculator() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var costOfService by remember { mutableStateOf(0.0) }

        val amount = calculateTip(costOfService)
        val tipAmount = stringResource(id = R.string.tip_amount, amount)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.activity_horizontal_margin),
                    vertical = dimensionResource(id = R.dimen.activity_vertical_margin)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LargeTitleWithText(resId = R.string.tip_calculator_title)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_default_spacing_large)))
            EditNumberField(
                value = costOfService,
                onValueChange = { costOfService = it },
                placeholderResId = R.string.hint_cost_of_service
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_default_spacing_large)))
            SmallTitleWithText(text = tipAmount)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    ComposePlaygroundTheme {
        TipCalculator()
    }
}

private fun calculateTip(costOfService: Double, tipPercent: Double = 0.15): String {
    val amount = costOfService * tipPercent
    return NumberFormat.getCurrencyInstance().format(amount)
}