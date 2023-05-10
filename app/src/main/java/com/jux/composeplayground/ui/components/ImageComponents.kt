package com.jux.composeplayground.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ImageWithDrawable(
    @DrawableRes resId: Int,
    modifier: Modifier = Modifier,
    description: String? = null
) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = description,
        modifier = modifier
    )
}