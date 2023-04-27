package com.jux.composeplayground.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

// region Text fields

@Composable
fun LargeTitleWithText(@StringRes resId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = resId),
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
fun SmallTitleWithText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier
    )
}

// endregion

// region Input fields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    value: Double,
    onValueChange: (Double) -> Unit,
    @StringRes placeholderResId: Int
) {
    TextField(
        value = value.toString(),
        onValueChange = { onValueChange(it.toDoubleOrNull() ?: 0.0) },
        label = { Text(text = stringResource(id = placeholderResId)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

// endregion