package com.jux.composeplayground.ui.tutorials.cupcake

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jux.composeplayground.R
import com.jux.composeplayground.ui.components.ButtonWithText
import com.jux.composeplayground.ui.components.DefaultScaffold
import com.jux.composeplayground.ui.components.MediumBodyWithText
import com.jux.composeplayground.ui.components.MediumTitleWithText
import com.jux.composeplayground.ui.components.OutlinedButtonWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun SummaryScreen(
    paddings: PaddingValues,
    orderUiState: OrderUiState,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        Pair(
            stringResource(id = R.string.quantity), pluralStringResource(
                id = R.plurals.cupcakes,
                count = orderUiState.quantity,
                orderUiState.quantity
            )
        ),
        Pair(stringResource(id = R.string.flavor), orderUiState.flavor),
        Pair(stringResource(id = R.string.pickup_date), orderUiState.date)
    )

    Column(
        modifier = modifier
            .padding(paddings)
            .padding(
                vertical = dimensionResource(id = R.dimen.activity_vertical_margin),
                horizontal = dimensionResource(id = R.dimen.activity_horizontal_margin)
            )
    ) {
        items.forEach { item ->
            SummaryItem(title = item.first, value = item.second)
        }
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.component_default_spacing)))
        MediumTitleWithText(
            text = stringResource(id = R.string.subtotal_price, orderUiState.price),
            modifier = modifier
                .align(Alignment.End)
                .padding(vertical = dimensionResource(id = R.dimen.component_default_spacing))
        )

        Column(modifier = modifier.weight(1f), verticalArrangement = Arrangement.Bottom) {
            ButtonWithText(resId = R.string.send, modifier = modifier.fillMaxWidth()) {

            }
            OutlinedButtonWithText(resId = R.string.cancel, modifier = modifier.fillMaxWidth()) {

            }
        }
    }
}

@Composable
fun SummaryItem(title: String, value: String, modifier: Modifier = Modifier) {
    Column {
        MediumBodyWithText(
            text = title.uppercase(),
            modifier.padding(bottom = dimensionResource(id = R.dimen.component_default_spacing))
        )
        MediumTitleWithText(text = value)
        Divider(modifier.padding(vertical = dimensionResource(id = R.dimen.component_default_spacing_large)))
    }
}

@Preview
@Composable
fun SummaryPreview() {
    ComposePlaygroundTheme {
        DefaultScaffold(topAppBarTitleResId = R.string.order_summary) {
            val uiState = OrderUiState(
                quantity = 6,
                flavor = "chocolate",
                date = "Monday June 6",
                price = "300"
            )
            SummaryScreen(paddings = it, uiState)
        }
    }
}