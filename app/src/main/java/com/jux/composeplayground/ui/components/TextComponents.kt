package com.jux.composeplayground.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

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

@Composable
fun SmallBodyWithText(@StringRes resId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = resId),
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}

@Composable
fun MediumLabelWithTextAndDrawableStart(
    text: String,
    @DrawableRes drawableResId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = drawableResId),
            contentDescription = text,
            colorFilter = ColorFilter.tint(color = Color.Black)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = modifier
        )
    }
}

// endregion

// region Input fields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    @StringRes placeholderResId: Int,
    imeAction: ImeAction? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = placeholderResId)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction ?: ImeAction.Default
        ),
        modifier = modifier
    )
}

// endregion