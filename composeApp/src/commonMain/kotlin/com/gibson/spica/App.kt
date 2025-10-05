package com.gibson.spica

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.spica.ui.*

/**
 * Root app composable (commonMain). Use this from all platform entry points.
 */
@Composable
fun App() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val selectedState = remember { mutableStateOf(0) }

            Column(modifier = Modifier.fillMaxSize()) {
                // Content
                Box(modifier = Modifier.weight(1f)) {
                    when (selectedState.value) {
                        0 -> HomeScreen()
                        1 -> PortfolioScreen()
                        2 -> WatchlistScreen()
                        3 -> MarketsScreen()
                        else -> HomeScreen()
                    }
                }

                // Bottom nav
                SpicaBottomNavBar(
                    modifier = Modifier.padding(bottom = 12.dp),
                    tabs = DefaultSpicaTabs,
                    selectedIndex = selectedState.value,
                    onTabSelected = { selectedState.value = it }
                )
            }
        }
    }
}
