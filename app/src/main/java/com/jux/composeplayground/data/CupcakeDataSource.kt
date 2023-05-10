package com.jux.composeplayground.data

import androidx.annotation.StringRes
import com.jux.composeplayground.R

object CupcakeDataSource {

    @StringRes
    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    val quantityOptions = listOf(1, 6, 12)
}