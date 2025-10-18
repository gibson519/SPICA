package com.gibson.spica.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.gibson.spica.data.AuthRepository // ✅ (You'll add this soon)

/**
 * Simple state-based router for SPICA.
 * Handles composable navigation without external dependencies.
 */
object Router {

    // ✅ Start on Home if already signed in, otherwise go to Login
    var currentRoute: String? by mutableStateOf(
        if (AuthRepository.currentUser != null) Screen.Home.route 
        else Screen.Login.route
    )
        private set

    fun navigate(route: String) {
        currentRoute = route
    }

    fun resetTo(route: String) {
        currentRoute = route
    }
}
