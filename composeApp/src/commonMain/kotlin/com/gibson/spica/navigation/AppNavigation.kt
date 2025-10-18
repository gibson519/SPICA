package com.gibson.spica.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gibson.spica.data.AuthRepository
import com.gibson.spica.data.FirebaseManager
import com.gibson.spica.ui.AppNavBar
import com.gibson.spica.ui.screens.*
import kotlinx.coroutines.tasks.await

@Composable
fun AppNavigation() {
    // ✅ Observe Firebase authentication state
    val userFlow = AuthRepository.currentUser.collectAsState(initial = AuthRepository.getCurrentUser())
    val user = userFlow.value
    val current = Router.currentRoute

    // ✅ On auth or user changes, decide where to go
    LaunchedEffect(user) {
        if (user == null) {
            Router.resetTo(Screen.Login.route)
            return@LaunchedEffect
        }

        if (!user.isEmailVerified) {
            Router.resetTo(Screen.EmailVerify.route)
            return@LaunchedEffect
        }

        // ✅ Check Firestore for account setup
        val userDoc = FirebaseManager.firestore.collection("users").document(user.uid).get().await()
        if (!userDoc.exists()) {
            Router.resetTo(Screen.AccountSetup.route)
            return@LaunchedEffect
        }

        // ✅ Everything is ready → go to main Home
        Router.resetTo(Screen.Home.route)
    }

    val mainScreens = listOf(
        Screen.Home.route,
        Screen.Portfolio.route,
        Screen.Watchlist.route,
        Screen.Markets.route
    )

    // ✅ Keep track of which tab is active
    val selectedState = remember(current) {
        mainScreens.indexOf(current).takeIf { it >= 0 } ?: 0
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (current) {
            Screen.Signup.route -> SignupScreen()
            Screen.Login.route -> LoginScreen()
            Screen.EmailVerify.route -> EmailVerifyScreen()
            Screen.PhoneVerify.route -> PhoneVerifyScreen()
            Screen.AccountSetup.route -> AccountSetupScreen()
            Screen.Home.route -> HomeScreen()
            Screen.Portfolio.route -> PortfolioScreen()
            Screen.Watchlist.route -> WatchlistScreen()
            Screen.Markets.route -> MarketsScreen()
            else -> HomeScreen()
        }

        // ✅ Only show bottom/side nav for main routes
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
