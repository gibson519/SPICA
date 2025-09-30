package com.gibson.spica.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.Person

sealed class NavigationItem(
    val title: String,
    val icon: ImageVector
) {
    object Home : NavigationItem("Home", Icons.Filled.Home)
    object Marketplace : NavigationItem("Marketplace", Icons.Filled.Public)
    object Portfolio : NavigationItem("Portfolio", Icons.Filled.Work)
    object Profile : NavigationItem("Profile", Icons.Filled.Person)

    companion object {
        val items = listOf(Home, Marketplace, Portfolio, Profile)
    }
}
