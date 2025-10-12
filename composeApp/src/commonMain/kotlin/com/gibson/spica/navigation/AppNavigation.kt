package com.gibson.spica.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.spica.ui.*

/**
 * Top-level navigation layout that places the platform AppNavBar
 * and switches between composable screens using the shared Router.
 */
@Composable
fun AppNavigation() {
    val current = Router.currentRoute

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 88.dp)
                ) {
                    when (current) {
                        Screen.Home.route -> HomeScreen()
                        Screen.Portfolio.route -> PortfolioScreen()
                        Screen.Watchlist.route -> WatchlistScreen()
                        Screen.Markets.route -> MarketsScreen()
                        else -> HomeScreen()
                    }
                }

                AppNavBar(
                    currentRoute = current,
                    onItemClick = { route -> Router.navigate(route) }
                )
            }
        }
    }
}
