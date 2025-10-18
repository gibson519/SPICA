package com.gibson.spica.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.navigation.compose.*
import com.gibson.spica.ui.NavBar
import com.gibson.spica.ui.SpicaTab
import com.gibson.spica.ui.screens.*

@Composable
actual fun MainScreen() {
    val navController = rememberNavController()

    val tabs = listOf(
        SpicaTab("Home", Icons.Default.Home),
        SpicaTab("Portfolio", Icons.Default.Wallet),
        SpicaTab("Watchlist", Icons.Default.PieChart),
        SpicaTab("Market", Icons.Default.Public)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("home") { HomeScreen() }
            composable("portfolio") { PortfolioScreen() }
            composable("watchlist") { WatchlistScreen() }
            composable("market") { MarketScreen() }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        ) {
            NavBar(
                tabs = tabs,
                selectedIndex = selectedIndex,
                onTabSelected = { index ->
                    selectedIndex = index
                    when (index) {
                        0 -> navController.navigate("home")
                        1 -> navController.navigate("portfolio")
                        2 -> navController.navigate("watchlist")
                        3 -> navController.navigate("market")
                    }
                }
            )
        }
    }
}
