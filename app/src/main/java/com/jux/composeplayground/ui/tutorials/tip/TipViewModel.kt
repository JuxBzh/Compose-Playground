package com.jux.composeplayground.ui.tutorials.tip

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import kotlin.math.ceil

class TipViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<TipUiState> = MutableStateFlow(TipUiState())
    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    var billAmount by mutableStateOf("")
        private set
    var tipPercentage by mutableStateOf("")
        private set
    var roundTipAmount by mutableStateOf(false)
        private set

    fun updateBillAmount(amount: String) {
        billAmount = amount
        calculateTipAmount()
    }

    fun updateTipPercentage(percentage: String) {
        tipPercentage = percentage
        calculateTipAmount()
    }

    fun updateRoundTip(round: Boolean) {
        roundTipAmount = round
        calculateTipAmount()
    }

    private fun calculateTipAmount() {
        val bill = billAmount.toDoubleOrNull() ?: 0.0
        val tipPercent = (tipPercentage.toDoubleOrNull() ?: 0.0) / 100.0
        var amount = bill * tipPercent
        if (roundTipAmount) {
            amount = ceil(amount)
        }
        _uiState.update { currentState ->
            currentState.copy(
                tipAmount = NumberFormat.getCurrencyInstance().format(amount)
            )
        }
    }
}