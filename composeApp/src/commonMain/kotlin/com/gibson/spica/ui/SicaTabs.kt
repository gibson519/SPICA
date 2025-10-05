package com.gibson.spica.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Work

val DefaultSpicaTabs = listOf(
    SpicaTab("Home", Icons.Default.Home),
    SpicaTab("Portfolio", Icons.Default.Work),
    SpicaTab("Watchlist", Icons.Default.List),
    SpicaTab("Markets", Icons.Default.Public)
)
