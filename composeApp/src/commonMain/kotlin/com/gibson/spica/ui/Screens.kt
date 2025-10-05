package com.gibson.spica.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    CenteredLabel("Home")
}

@Composable
fun PortfolioScreen() {
    CenteredLabel("Portfolio")
}

@Composable
fun WatchlistScreen() {
    CenteredLabel("Watchlist")
}

@Composable
fun MarketsScreen() {
    CenteredLabel("Markets")
}

@Composable
private fun CenteredLabel(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text, style = MaterialTheme.typography.titleLarge)
    }
}
