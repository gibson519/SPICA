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
 * Responsive navigation layout for SPICA.
 *
 * - Phones: Bottom nav bar (SpicaBottomNavBar)
 * - Tablets: Side nav bar (SpicaSideNav)
 * - Desktops/Web: Side nav bar (left/right via platform actual)
 */
@Composable
fun AppNavigation() {
    val current = Router.currentRoute

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val isWideScreen = maxWidth > 600.dp  // ✅ Adaptive switch

                if (isWideScreen) {
                    // Side navigation layout
                    Row(modifier = Modifier.fillMaxSize()) {
                        // Left side nav
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(92.dp)
                        ) {
                            SpicaSideNav(
                                tabs = DefaultSpicaTabs,
                                selectedIndex = when (current) {
                                    Screen.Home.route -> 0
                                    Screen.Portfolio.route -> 1
                                    Screen.Watchlist.route -> 2
                                    Screen.Markets.route -> 3
                                    else -> 0
                                },
                                onTabSelected = { index ->
                                    Router.navigate(
                                        when (index) {
                                            0 -> Screen.Home.route
                                            1 -> Screen.Portfolio.route
                                            2 -> Screen.Watchlist.route
                                            3 -> Screen.Markets.route
                                            else -> Screen.Home.route
                                        }
                                    )
                                }
                            )
                        }

                        // Main screen content
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(16.dp)
                        ) {
                            when (current) {
                                Screen.Home.route -> HomeScreen()
                                Screen.Portfolio.route -> PortfolioScreen()
                                Screen.Watchlist.route -> WatchlistScreen()
                                Screen.Markets.route -> MarketsScreen()
                                else -> HomeScreen()
                            }
                        }
                    }
                } else {
                    // Mobile layout → bottom navigation bar
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

                        SpicaBottomNavBar(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 12.dp),
                            tabs = DefaultSpicaTabs,
                            selectedIndex = when (current) {
                                Screen.Home.route -> 0
                                Screen.Portfolio.route -> 1
                                Screen.Watchlist.route -> 2
                                Screen.Markets.route -> 3
                                else -> 0
                            },
                            onTabSelected = { index ->
                                Router.navigate(
                                    when (index) {
                                        0 -> Screen.Home.route
                                        1 -> Screen.Portfolio.route
                                        2 -> Screen.Watchlist.route
                                        3 -> Screen.Markets.route
                                        else -> Screen.Home.route
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
