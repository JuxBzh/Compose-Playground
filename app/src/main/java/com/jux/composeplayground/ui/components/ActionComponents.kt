package com.jux.composeplayground.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jux.composeplayground.R

@Composable
fun SwitchWithText(
    modifier: Modifier = Modifier,
    @StringRes textResId: Int,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = textResId))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
        )
    }
}

// region Buttons

@Composable
fun ButtonWithText(
    @StringRes resId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(text = stringResource(id = resId))
    }
}

@Composable
fun OutlinedButtonWithText(
    @StringRes resId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors()
    ) {
        Text(text = stringResource(id = resId))
    }
}

@Composable
fun TextButtonWithText(
    @StringRes resId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors()
    ) {
        Text(text = stringResource(id = resId))
    }
}

@Composable
fun RadioButtonWithText(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: (String) -> Unit = {}
) {
    Row(
        modifier = modifier.selectable(selected = selected, onClick = { onClick(text) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = { onClick(text) })
        Text(text = text)
    }
}

@Composable
fun NavigationButtonBar(
    modifier: Modifier = Modifier,
    onPrevious: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.component_default_spacing))
    ) {
        OutlinedButtonWithText(
            resId = R.string.cancel,
            onClick = onPrevious,
            modifier = modifier.weight(1f)
        )
        ButtonWithText(
            resId = R.string.next,
            onClick = onNext,
            modifier = modifier.weight(1f)
        )
    }
}

// endregion