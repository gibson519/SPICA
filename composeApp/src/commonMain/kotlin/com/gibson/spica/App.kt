package com.gibson.spica

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.spica.ui.*

/**
 * Root composable for all platforms.
 * Uses platform-specific navigation layouts:
 * - Android/iOS: Bottom nav bar
 * - Web: Left side nav
 * - Desktop: Right side nav
 */
@Composable
fun App(platform: String = "Android") { // pass "web" or "desktop" manually in those builds
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val selectedIndex = remember { mutableStateOf(0) }

            when (platform) {

                // --------------------------------
                // 📱 MOBILE (Android + iOS)
                // --------------------------------
                "Android", "iOS" -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 88.dp) // space for bottom nav
                        ) {
                            when (selectedIndex.value) {
                                0 -> HomeScreen()
                                1 -> PortfolioScreen()
                                2 -> WatchlistScreen()
                                3 -> MarketsScreen()
                            }
                        }

                        SpicaBottomNavBar(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 12.dp),
                            tabs = DefaultSpicaTabs,
                            selectedIndex = selectedIndex.value,
                            onTabSelected = { selectedIndex.value = it }
                        )
                    }
                }

                // --------------------------------
                // 🌐 WEB (Left side nav)
                // --------------------------------
                "Web" -> {
                    Row(modifier = Modifier.fillMaxSize()) {
                        SpicaSideNavLeft(
                            tabs = DefaultSpicaTabs,
                            selectedIndex = selectedIndex.value,
                            onTabSelected = { selectedIndex.value = it }
                        )

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(24.dp)
                        ) {
                            when (selectedIndex.value) {
                                0 -> HomeScreen()
                                1 -> PortfolioScreen()
                                2 -> WatchlistScreen()
                                3 -> MarketsScreen()
                            }
                        }
                    }
                }

                // --------------------------------
                // 🖥️ DESKTOP (Right side nav)
                // --------------------------------
                "Java" -> {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(24.dp)
                        ) {
                            when (selectedIndex.value) {
                                0 -> HomeScreen()
                                1 -> PortfolioScreen()
                                2 -> WatchlistScreen()
                                3 -> MarketsScreen()
                            }
                        }

                        SpicaSideNavRight(
                            tabs = DefaultSpicaTabs,
                            selectedIndex = selectedIndex.value,
                            onTabSelected = { selectedIndex.value = it }
                        )
                    }
                }

                else -> {
                    // Fallback (default to mobile)
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 88.dp)
                        ) {
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}
