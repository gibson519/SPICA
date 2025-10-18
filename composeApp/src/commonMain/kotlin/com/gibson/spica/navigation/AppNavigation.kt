package com.gibson.spica.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gibson.spica.ui.AppNavBar
import com.gibson.spica.ui.screens.*

@Composable
fun AppNavigation() {
    val current = Router.currentRoute

    val mainScreens = listOf(
        Screen.Home.route,
        Screen.Portfolio.route,
        Screen.Watchlist.route,
        Screen.Markets.route
    )

    // Determine selected tab index based on current route
    val selectedState = remember(current) {
        mainScreens.indexOf(current).takeIf { it >= 0 } ?: 0
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (current) {
            Screen.Signup.route -> SignupScreen()
            Screen.Login.route -> LoginScreen()
            Screen.AccountSetup.route -> AccountSetupScreen()
            Screen.EmailVerify.route -> EmailVerifyScreen()
            Screen.PhoneVerify.route -> PhoneVerifyScreen()
            Screen.Home.route -> HomeScreen()
            Screen.Portfolio.route -> PortfolioScreen()
            Screen.Watchlist.route -> WatchlistScreen()
            Screen.Markets.route -> MarketsScreen()
            else -> HomeScreen()
        }

        // ✅ Show nav bar only for main pages
        if (current in mainScreens) {
            AppNavBar(
                selectedIndex = selectedState,
                onTabSelected = { index ->
                    val newRoute = mainScreens[index]
                    if (newRoute != current) Router.navigate(newRoute)
                }
            )
        }
    }
}
