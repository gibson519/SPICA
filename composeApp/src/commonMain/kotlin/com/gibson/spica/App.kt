package com.gibson.spica

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.gibson.spica.ui.*

/**
Root app composable (commonMain)
*/
@Composable
fun App() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val selectedState = remember { mutableStateOf(0) }

            // Use existing shared getPlatform() to decide layout.
            val platformName = getPlatform().name.lowercase()
            val isWeb = platformName.contains("web") || platformName.contains("kotlin/js")
            val isDesktop = platformName.startsWith("java") || platformName.contains("jvm")

            when {
                isWeb -> {
                    // Left side nav for web
                    Row(modifier = Modifier.fillMaxSize()) {
                        SpicaSideNav(
                            modifier = Modifier.fillMaxHeight().width(92.dp),
                            tabs = DefaultSpicaTabs,
                            selectedIndex = selectedState.value,
                            onTabSelected = { selectedState.value = it }
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(16.dp)
                        ) {
                            when (selectedState.value) {
                                0 -> com.gibson.spica.ui.HomeScreen()
                                1 -> com.gibson.spica.ui.PortfolioScreen()
                                2 -> com.gibson.spica.ui.WatchlistScreen()
                                3 -> com.gibson.spica.ui.MarketsScreen()
                                else -> com.gibson.spica.ui.HomeScreen()
                            }
                        }
                    }
                }

                isDesktop -> {
                    // Right side nav for desktop
                    Row(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(16.dp)
                        ) {
                            when (selectedState.value) {
                                0 -> com.gibson.spica.ui.HomeScreen()
                                1 -> com.gibson.spica.ui.PortfolioScreen()
                                2 -> com.gibson.spica.ui.WatchlistScreen()
                                3 -> com.gibson.spica.ui.MarketsScreen()
                                else -> com.gibson.spica.ui.HomeScreen()
                            }
                        }
                        SpicaSideNav(
                            modifier = Modifier.fillMaxHeight().width(92.dp),
                            tabs = DefaultSpicaTabs,
                            selectedIndex = selectedState.value,
                            onTabSelected = { selectedState.value = it }
                        )
                    }
                }

                else -> {
                    // Mobile / fallback: keep bottom nav (existing behavior)
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Reserve space at the bottom for the nav by padding the content column.
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 88.dp)) {

                            when (selectedState.value) {
                                0 -> com.gibson.spica.ui.HomeScreen()
                                1 -> com.gibson.spica.ui.PortfolioScreen()
                                2 -> com.gibson.spica.ui.WatchlistScreen()
                                3 -> com.gibson.spica.ui.MarketsScreen()
                                else -> com.gibson.spica.ui.HomeScreen()
                            }
                        }

                        // Bottom nav aligned to the bottom center (existing)
                        SpicaBottomNavBar(
                            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 12.dp),
                            tabs = DefaultSpicaTabs,
                            selectedIndex = selectedState.value,
                            onTabSelected = { selectedState.value = it }
                        )
                    }
                }
            }
        }
    }
}
