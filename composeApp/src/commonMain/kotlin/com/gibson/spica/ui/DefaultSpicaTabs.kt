package com.gibson.spica.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Wallet

// ✅ For bottom navigation
val DefaultSpicaTabs = listOf(
    SpicaTab("Home", Icons.Default.Home),
    SpicaTab("Portfolio", Icons.Default.Wallet),
    SpicaTab("Watchlist", Icons.Default.PieChart),
    SpicaTab("Markets", Icons.Default.Public)
)

// ✅ For side navigation (shares same data)
val SpicaSideTabs = listOf(
    SpicaSideTab("Home", Icons.Default.Home),
    SpicaSideTab("Portfolio", Icons.Default.Wallet),
    SpicaSideTab("Watchlist", Icons.Default.PieChart),
    SpicaSideTab("Markets", Icons.Default.Public)
)
