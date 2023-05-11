package com.jux.composeplayground.ui.utils

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

fun NavController.assertCurrentRouteName(name: String) {
    assertEquals("Wrong route name", name, currentBackStackEntry?.destination?.route)
}