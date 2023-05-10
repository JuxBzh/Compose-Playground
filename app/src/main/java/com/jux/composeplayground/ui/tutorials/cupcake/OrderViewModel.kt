package com.jux.composeplayground.ui.tutorials.cupcake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<OrderUiState> =
        MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    var isFlavorStepCompleted by mutableStateOf(false)
        private set

    var isPickupStepCompleted by mutableStateOf(false)
        private set

    fun updateQuantity(quantity: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = quantity,
                price = calculatePrice(quantity = quantity)
            )
        }
    }

    fun updateFlavor(flavor: String) {
        isFlavorStepCompleted = flavor.isNotEmpty()
        _uiState.update { currentState ->
            currentState.copy(flavor = flavor)
        }
    }

    fun updatePickupDate(date: String) {
        isPickupStepCompleted = date.isNotEmpty()
        _uiState.update { currentState ->
            currentState.copy(
                date = date,
                price = calculatePrice(pickupDate = date)
            )
        }
    }

    fun resetOrder() {
        isFlavorStepCompleted = false
        isPickupStepCompleted = false
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }

    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        return NumberFormat.getCurrencyInstance().format(calculatedPrice)
    }

    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // add current date and the following 3 dates.
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }
}