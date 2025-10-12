package com.gibson.spica.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.runtime.Composable
import com.gibson.spica.navigation.Screen

@Composable
actual fun AppNavBar(
    currentRoute: String?,
    onItemClick: (route: String) -> Unit
) {
    val tabs = listOf(
        SpicaTab("Home", Icons.Default.Home),
        SpicaTab("Portfolio", Icons.Default.Wallet),
        SpicaTab("Watchlist", Icons.Default.PieChart),
        SpicaTab("Markets", Icons.Default.Public)
    )

    val selectedIndex = when (currentRoute) {
        Screen.Home.route -> 0
        Screen.Portfolio.route -> 1
        Screen.Watchlist.route -> 2
        Screen.Markets.route -> 3
        else -> 0
    }

    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        SpicaBottomNavBar(
            tabs = tabs,
            selectedIndex = selectedIndex,
            onTabSelected = { index ->
                onItemClick(
                    when (index) {
                        0 -> Screen.Home.route
                        1 -> Screen.Portfolio.route
                        2 -> Screen.Watchlist.route
                        3 -> Screen.Markets.route
                        else -> Screen.Home.route
                    }
                )
            }
        )
    }
}
