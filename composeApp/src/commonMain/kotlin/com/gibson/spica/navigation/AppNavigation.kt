package com.gibson.spica.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gibson.spica.ui.AppNavBar
import com.gibson.spica.ui.HomeScreen
import com.gibson.spica.ui.PortfolioScreen
import com.gibson.spica.ui.WatchlistScreen
import com.gibson.spica.ui.MarketsScreen

@Composable
fun AppNavigation() {
    var selectedState by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Content area
        Column(modifier = Modifier.fillMaxSize()) {
            when (selectedState) {
                0 -> HomeScreen()
                1 -> PortfolioScreen()
                2 -> WatchlistScreen()
                3 -> MarketsScreen()
                else -> HomeScreen()
            }
        }

        // Platform-specific nav bar (expect/actual)
        AppNavBar(
            selectedIndex = selectedState,
            onTabSelected = { selectedState = it }
        )
    }
}
