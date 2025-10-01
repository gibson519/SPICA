package com.gibson.spica.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(
    val title: String,
    val icon: ImageVector
) {
    object Home : NavItem("Home", Icons.Filled.Home)
    object Portfolio : NavItem("Portfolio", Icons.Filled.Work)
    object Watchlist : NavItem("Watchlist", Icons.Filled.Person)
    object Markets : NavItem("Markets", Icons.Filled.Public)

    companion object {
        val items = listOf(Home, Portfolio, Watchlist, Markets)
    }
}
