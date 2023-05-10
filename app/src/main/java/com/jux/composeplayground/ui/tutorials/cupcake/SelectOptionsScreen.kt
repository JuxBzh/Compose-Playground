package com.jux.composeplayground.ui.tutorials.cupcake

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jux.composeplayground.R
import com.jux.composeplayground.data.CupcakeDataSource
import com.jux.composeplayground.ui.components.MediumTitleWithText
import com.jux.composeplayground.ui.components.NavigationButtonBar
import com.jux.composeplayground.ui.components.RadioButtonWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun SelectOptionScreen(
    subtotal: String,
    options: List<String>,
    isNextEnabled: Boolean,
    modifier: Modifier = Modifier,
    onSelectionChange: (String) -> Unit = {},
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    var selectedOption by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.activity_vertical_margin),
                horizontal = dimensionResource(id = R.dimen.activity_horizontal_margin)
            )
    ) {
        Column {
            options.forEach { option ->
                RadioButtonWithText(
                    text = option,
                    selected = selectedOption == option,
                    onClick = {
                        selectedOption = it
                        onSelectionChange(selectedOption)
                    }
                )
            }
            Divider(modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.component_default_spacing)))
            MediumTitleWithText(
                text = stringResource(id = R.string.subtotal_price, subtotal),
                modifier = modifier
                    .align(Alignment.End)
                    .padding(vertical = dimensionResource(id = R.dimen.component_default_spacing))
            )
        }
        NavigationButtonBar(
            modifier = modifier.weight(1f),
            isNextEnabled = isNextEnabled,
            onPrevious = onPreviousClick,
            onNext = onNextClick
        )
    }
}

@Preview
@Composable
fun SelectOptionsPreview() {
    ComposePlaygroundTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val flavors = CupcakeDataSource.flavors.map { resId -> stringResource(id = resId) }
            SelectOptionScreen("300", flavors, isNextEnabled = false)
        }
    }
}