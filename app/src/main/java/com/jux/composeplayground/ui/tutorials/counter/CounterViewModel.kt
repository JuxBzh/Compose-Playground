package com.jux.composeplayground.ui.tutorials.counter

import com.airbnb.mvrx.MavericksViewModel

class CounterViewModel(initialState: CounterState) :
    MavericksViewModel<CounterState>(initialState) {

    fun increase() {
        setState {
            val newCount = count + 1
            copy(count = newCount, canDecrease = newCount > 0)
        }
    }

    fun decrease() {
        setState {
            val newCount = count - 1
            copy(count = newCount, canDecrease = newCount > 0)
        }
    }
}