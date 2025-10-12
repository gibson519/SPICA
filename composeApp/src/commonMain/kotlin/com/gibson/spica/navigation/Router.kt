package com.gibson.spica.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Simple state-based router for SPICA.
 * Handles composable navigation without external dependencies.
 */
object Router {
    var currentRoute: String? by mutableStateOf(Screen.Home.route)
        private set

    fun navigate(route: String) {
        currentRoute = route
    }

    fun resetTo(route: String) {
        currentRoute = route
    }
}
