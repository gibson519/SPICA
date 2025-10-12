package com.gibson.spica.ui

import androidx.compose.runtime.Immutable
import com.gibson.spica.navigation.Screen

@Immutable
data class NavItem(val route: String, val label: String)

/**
 * Shared navigation items across all platforms.
 */
val MainNavItems = listOf(
    NavItem(route = Screen.Home.route, label = "Home"),
    NavItem(route = Screen.Portfolio.route, label = "Portfolio"),
    NavItem(route = Screen.Watchlist.route, label = "Watchlist"),
    NavItem(route = Screen.Markets.route, label = "Markets")
)
