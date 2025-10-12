package com.gibson.spica.navigation

/**
 * Defines all app screens and their routes for SPICA.
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Portfolio : Screen("portfolio")
    object Watchlist : Screen("watchlist")
    object Markets : Screen("markets")

    companion object {
        fun fromRoute(route: String?): Screen? = when (route) {
            Home.route -> Home
            Portfolio.route -> Portfolio
            Watchlist.route -> Watchlist
            Markets.route -> Markets
            else -> null
        }
    }
}
