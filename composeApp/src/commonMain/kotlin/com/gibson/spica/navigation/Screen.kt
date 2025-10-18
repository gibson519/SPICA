package com.gibson.spica.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gibson.spica.ui.AppNavBar
import com.gibson.spica.ui.screens.*

@Composable
fun AppNavigation() {
    var selectedState by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        // --- Screen content ---
        Column(modifier = Modifier.fillMaxSize()) {
            when (selectedState) {
                0 -> HomeScreen()
                1 -> PortfolioScreen()
                2 -> WatchlistScreen()
                3 -> MarketsScreen()
                else -> when (Router.currentRoute) {
                    Screen.Signup.route -> SignupScreen()
                    Screen.Login.route -> LoginScreen()
                    Screen.EmailVerify.route -> EmailVerifyScreen()
                    Screen.AccountSetup.route -> AccountSetupScreen()
                    else -> HomeScreen()
                }
            }
        }

        // --- Platform-specific navigation bar ---
        AppNavBar(
            selectedIndex = selectedState,
            onTabSelected = { selectedState = it }
        )
    }
}
