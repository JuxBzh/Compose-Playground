package com.jux.composeplayground.ui.tutorials.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.jux.composeplayground.R
import com.jux.composeplayground.ui.tutorials.cupcake.CupcakeScreen.FLAVOR
import com.jux.composeplayground.ui.tutorials.cupcake.CupcakeScreen.START
import com.jux.composeplayground.ui.utils.assertCurrentRouteName
import com.jux.composeplayground.ui.utils.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CupcakeScreenNavigationTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeOrderFunnel(navController = navController)
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        // THEN
        navController.assertCurrentRouteName(START.name)
    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        // GIVEN
        val backText = composeTestRule.activity.getString(R.string.back_button)

        // THEN
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigateToSelectFlavorScreen() {
        // GIVEN
        val text = composeTestRule.activity.resources.getQuantityString(R.plurals.cupcakes, 1, 1)
        val node = composeTestRule.onNodeWithText(text)

        // WHEN
        node.performClick()

        // THEN
        navController.assertCurrentRouteName(FLAVOR.name)
    }

    @Test
    fun cupcakeNavHost_clickSixCupcakes_navigateToSelectFlavorScreen() {
        // GIVEN
        val text = composeTestRule.activity.resources.getQuantityString(R.plurals.cupcakes, 6, 6)
        val node = composeTestRule.onNodeWithText(text)

        // WHEN
        node.performClick()

        // THEN
        navController.assertCurrentRouteName(FLAVOR.name)
    }

    @Test
    fun cupcakeNavHost_clickTwelveCupcakes_navigateToSelectFlavorScreen() {
        // GIVEN
        val text = composeTestRule.activity.resources.getQuantityString(R.plurals.cupcakes, 12, 12)
        val node = composeTestRule.onNodeWithText(text)

        // WHEN
        node.performClick()

        // THEN
        navController.assertCurrentRouteName(FLAVOR.name)
    }

    @Test
    fun cupcakeNavHost_navigateToFlavorScreen_verifyNextButtonDisabled() {
        // GIVEN
        navigateToFlavorScreen()

        // THEN
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun cupcakeNavHost_navigateToFlavorScreen_goBackToStartOrderByClickingNavigateUpButton() {
        // GIVEN
        navigateToFlavorScreen()

        // WHEN
        clickNavigateUpButton()

        // THEN
        navController.assertCurrentRouteName(START.name)
    }

    @Test
    fun cupcakeNavHost_navigateToFlavorScreen_goBackToStartOrderByClickingCancelButton() {
        // GIVEN
        navigateToFlavorScreen()

        // WHEN
        clickCancelButton()

        // THEN
        navController.assertCurrentRouteName(START.name)
    }

    // region Helper functions

    private fun navigateToFlavorScreen() {
        val text = composeTestRule.activity.resources.getQuantityString(R.plurals.cupcakes, 1, 1)
        composeTestRule.onNodeWithText(text).performClick()
    }

    private fun clickNavigateUpButton() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }

    private fun clickCancelButton() {
        composeTestRule.onNodeWithStringId(R.string.cancel).performClick()
    }

    // endregion
}