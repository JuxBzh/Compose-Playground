package com.jux.composeplayground.ui.tutorials.counter

import com.airbnb.mvrx.MavericksState

data class CounterState(
    val count: Int = 0,
    val canDecrease: Boolean = false
) : MavericksState
