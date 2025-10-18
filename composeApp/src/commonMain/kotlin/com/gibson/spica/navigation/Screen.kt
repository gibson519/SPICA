package com.gibson.spica.navigation

sealed class Screen(val route: String) {
    // Onboarding
    object Signup : Screen("signup")
    object Login : Screen("login")
    object AccountSetup : Screen("account_setup")
    object EmailVerify : Screen("email_verify")
    object PhoneVerify : Screen("phone_verify")

    // Main
    object Home : Screen("home")
    object Portfolio : Screen("portfolio")
    object Watchlist : Screen("watchlist")
    object Markets : Screen("markets")

    companion object {
        fun fromRoute(route: String?): Screen? = when (route) {
            Signup.route -> Signup
            Login.route -> Login
            AccountSetup.route -> AccountSetup
            EmailVerify.route -> EmailVerify
            PhoneVerify.route -> PhoneVerify
            Home.route -> Home
            Portfolio.route -> Portfolio
            Watchlist.route -> Watchlist
            Markets.route -> Markets
            else -> null
        }
    }
}
