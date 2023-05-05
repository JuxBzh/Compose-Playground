package com.jux.composeplayground.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val nameResId: Int,
    @DrawableRes val imageResId: Int,
    val numberOfCourses: Int
)
