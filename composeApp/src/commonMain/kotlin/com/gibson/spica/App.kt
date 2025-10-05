package com.gibson.spica

import androidx.compose.runtime.*
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gibson.spica.navigation.*
import com.gibson.spica.ui.screens.*

@Composable
fun App() {
    var selectedItem by remember { mutableStateOf<NavItem>(NavItem.Home) }

    Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
        PlatformNavContainer(
            selectedItem = selectedItem,
            onItemSelected = { selectedItem = it }
        ) {
            when (selectedItem) {
                is NavItem.Home -> HomeScreen()
                is NavItem.Portfolio -> PortfolioScreen()
                is NavItem.Watchlist -> WatchlistScreen()
                is NavItem.Markets -> MarketsScreen()
            }
        }
    }
}
