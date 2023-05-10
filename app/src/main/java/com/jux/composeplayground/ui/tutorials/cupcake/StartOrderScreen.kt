package com.jux.composeplayground.ui.tutorials.cupcake

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jux.composeplayground.R
import com.jux.composeplayground.data.CupcakeDataSource
import com.jux.composeplayground.ui.components.ButtonWithText
import com.jux.composeplayground.ui.components.ImageWithDrawable
import com.jux.composeplayground.ui.components.LargeHeadlineWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun StartOrderScreen(
    quantityOptions: List<Int>,
    onQuantitySelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                vertical = dimensionResource(id = R.dimen.activity_vertical_margin),
                horizontal = dimensionResource(id = R.dimen.activity_horizontal_margin)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageWithDrawable(resId = R.drawable.cupcake)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_default_spacing_xlarge)))
        LargeHeadlineWithText(resId = R.string.order_cupcakes)
        Spacer(modifier = modifier.weight(1f))

        quantityOptions.forEach { quantity ->
            ButtonWithText(
                text = pluralStringResource(id = R.plurals.cupcakes, count = quantity, quantity),
                modifier = modifier.fillMaxWidth()
            ) {
                onQuantitySelected(quantity)
            }
        }
    }
}

@Preview
@Composable
fun StartOrderPreview() {
    ComposePlaygroundTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StartOrderScreen(CupcakeDataSource.quantityOptions, onQuantitySelected = {})
        }
    }
}