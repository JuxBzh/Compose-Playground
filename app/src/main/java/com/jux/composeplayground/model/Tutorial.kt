package com.jux.composeplayground.model

import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tutorial(
    @StringRes val nameResId: Int,
    @DrawableRes val iconResId: Int,
    val activity: Class<out ComponentActivity>
)
