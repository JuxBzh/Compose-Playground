package com.jux.composeplayground.ui.tutorials.cupcake

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jux.composeplayground.R
import com.jux.composeplayground.data.CupcakeDataSource
import com.jux.composeplayground.ui.components.DefaultScaffold
import com.jux.composeplayground.ui.tutorials.cupcake.CupcakeScreen.FLAVOR
import com.jux.composeplayground.ui.tutorials.cupcake.CupcakeScreen.PICKUP
import com.jux.composeplayground.ui.tutorials.cupcake.CupcakeScreen.START
import com.jux.composeplayground.ui.tutorials.cupcake.CupcakeScreen.SUMMARY

@Composable
fun CupcakeOrderFunnel(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: START.name

    DefaultScaffold(
        topAppBarTitleResId = appBarTitleFromScreen(currentScreen),
        canNavigateBack = navController.previousBackStackEntry != null,
        navigateUp = { navController.popBackStack() }
    ) {
        NavHost(
            navController = navController,
            startDestination = START.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = START.name) {
                StartOrderScreen(
                    quantityOptions = CupcakeDataSource.quantityOptions,
                    onQuantitySelected = { quantity ->
                        viewModel.updateQuantity(quantity)
                        navController.navigate(FLAVOR.name)
                    }
                )
            }

            composable(route = FLAVOR.name) {
                val context = LocalContext.current
                val options = CupcakeDataSource.flavors.map { resId -> context.getString(resId) }
                SelectOptionScreen(
                    subtotal = uiState.price,
                    options = options,
                    isNextEnabled = viewModel.isFlavorStepCompleted,
                    onSelectionChange = { flavor -> viewModel.updateFlavor(flavor) },
                    onPreviousClick = { navController.popBackStack() },
                    onNextClick = { navController.navigate(PICKUP.name) }
                )
            }

            composable(route = PICKUP.name) {
                SelectOptionScreen(
                    subtotal = uiState.price,
                    options = uiState.pickupOptions,
                    isNextEnabled = viewModel.isPickupStepCompleted,
                    onSelectionChange = { flavor -> viewModel.updatePickupDate(flavor) },
                    onPreviousClick = { navController.popBackStack() },
                    onNextClick = { navController.navigate(SUMMARY.name) }
                )
            }

            composable(route = SUMMARY.name) {
                SummaryScreen(
                    orderUiState = uiState,
                    onSendClick = {},
                    onCancelClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(START.name, inclusive = false)
                    }
                )
            }
        }
    }
}

@StringRes
private fun appBarTitleFromScreen(name: String): Int = when (CupcakeScreen.valueOf(name)) {
    START -> R.string.order_cupcakes
    FLAVOR -> R.string.choose_flavor
    PICKUP -> R.string.choose_pickup_date
    SUMMARY -> R.string.order_summary
}

enum class CupcakeScreen {
    START, FLAVOR, PICKUP, SUMMARY
}