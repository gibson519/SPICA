package com.gibson.spica.navigation

import androidx.compose.runtime.Composable
import com.gibson.spica.ui.*

@Composable
fun NavigationHost(selectedItem: NavigationItem) {
    when (selectedItem) {
        is NavigationItem.Home -> HomeScreen()
        is NavigationItem.Marketplace -> MarketplaceScreen()
        is NavigationItem.Portfolio -> PortfolioScreen()
        is NavigationItem.Profile -> ProfileScreen()
    }
}
