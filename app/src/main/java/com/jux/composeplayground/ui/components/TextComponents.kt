package com.jux.composeplayground.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
fun LargeDisplayWithText(
    @StringRes resId: Int,
    modifier: Modifier = Modifier,
    color: Color? = null
) {
    Text(
        text = stringResource(id = resId),
        style = MaterialTheme.typography.displayLarge,
        color = color ?: Color.Unspecified,
        modifier = modifier
    )
}

@Composable
fun MediumDisplayWithText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.displayMedium,
        modifier = modifier
    )
}

@Composable
fun LargeTitleWithText(@StringRes resId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = resId),
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
fun MediumTitleWithText(@StringRes resId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = resId),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun MediumTitleWithText(text: String, modifier: Modifier = Modifier, color: Color? = null) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier,
        color = color ?: Color.Unspecified
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
fun MediumBodyWithText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
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

@Composable
fun MediumHeadlineWithText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
fun LargeHeadlineWithText(@StringRes resId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = resId),
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier
    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    @StringRes placeholderResId: Int,
    isError: Boolean = false
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.surface),
        label = { Text(text = stringResource(id = placeholderResId)) },
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { onKeyboardDone() })
    )
}

// endregion