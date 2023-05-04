package com.jux.composeplayground.ui.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.jux.composeplayground.R
import com.jux.composeplayground.ui.components.EditNumberField
import com.jux.composeplayground.ui.components.LargeTitleWithText
import com.jux.composeplayground.ui.components.SmallTitleWithText
import com.jux.composeplayground.ui.components.SwitchWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme
import java.text.NumberFormat
import kotlin.math.ceil

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
        var billAmount by remember { mutableStateOf("") }
        var tipPercent by remember { mutableStateOf("") }
        var roundUpTip by remember { mutableStateOf(false) }

        val bill = billAmount.toDoubleOrNull() ?: 0.0
        val tip = (tipPercent.toDoubleOrNull() ?: 0.0) / 100.0
        val amount = calculateTip(bill, tip, roundUpTip)
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
                value = billAmount,
                onValueChange = { billAmount = it },
                placeholderResId = R.string.hint_bill_amount,
                imeAction = ImeAction.Next,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_default_spacing_large)))
            EditNumberField(
                value = tipPercent,
                onValueChange = { tipPercent = it },
                placeholderResId = R.string.hint_tip_percentage,
                imeAction = ImeAction.Done,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_default_spacing_large)))
            SwitchWithText(
                textResId = R.string.action_round_up_tip,
                checked = roundUpTip,
                onCheckedChange = { roundUpTip = it })
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

private fun calculateTip(
    costOfService: Double,
    tipPercent: Double,
    roundUp: Boolean = false
): String {
    var amount = costOfService * tipPercent
    if (roundUp) {
        amount = ceil(amount)
    }
    return NumberFormat.getCurrencyInstance().format(amount)
}