package com.jux.composeplayground.ui.tutorials.tip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jux.composeplayground.R
import com.jux.composeplayground.ui.components.DefaultPage
import com.jux.composeplayground.ui.components.EditNumberField
import com.jux.composeplayground.ui.components.LargeTitleWithText
import com.jux.composeplayground.ui.components.SmallTitleWithText
import com.jux.composeplayground.ui.components.SwitchWithText

@Composable
fun TipCalculator(viewModel: TipViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    val tipAmount = stringResource(id = R.string.tip_amount, uiState.tipAmount)

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
            value = viewModel.billAmount,
            onValueChange = { viewModel.updateBillAmount(it) },
            placeholderResId = R.string.hint_bill_amount,
            imeAction = ImeAction.Next,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_default_spacing_large)))
        EditNumberField(
            value = viewModel.tipPercentage,
            onValueChange = { viewModel.updateTipPercentage(it) },
            placeholderResId = R.string.hint_tip_percentage,
            imeAction = ImeAction.Done,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_default_spacing_large)))
        SwitchWithText(
            textResId = R.string.action_round_up_tip,
            checked = viewModel.roundTipAmount,
            onCheckedChange = { viewModel.updateRoundTip(it) })
        SmallTitleWithText(text = tipAmount)
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    DefaultPage {
        TipCalculator()
    }
}