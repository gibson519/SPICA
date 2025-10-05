package com.gibson.spica

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gibson.spica.navigation.NavItem
import com.gibson.spica.navigation.PlatformNavBar
import com.gibson.spica.ui.screens.*

@Composable
fun App() {
    // ✅ Always initialize with a default non-null NavItem
    var selectedItem by remember { mutableStateOf<NavItem>(NavItem.Home) }

    val platformName = getPlatform().name

    Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxSize()) {

            if (platformName.startsWith("Java") || platformName.startsWith("Web")) {
                PlatformNavBar(
                    selectedItem = selectedItem,
                    onItemSelected = { selectedItem = it }
                )
            }

            Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    when (selectedItem) {
                        is NavItem.Home -> HomeScreen()
                        is NavItem.Portfolio -> PortfolioScreen()
                        is NavItem.Watchlist -> WatchlistScreen()
                        is NavItem.Markets -> MarketsScreen()
                    }
                }

                if (platformName.startsWith("Android") || platformName.startsWith("iOS")) {
                    PlatformNavBar(
                        selectedItem = selectedItem,
                        onItemSelected = { selectedItem = it }
                    )
                }
            }
        }
    }
}
