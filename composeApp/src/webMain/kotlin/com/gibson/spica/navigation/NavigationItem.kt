package com.gibson.spica.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Home
import androidx.compose.material3.icons.filled.Star
import androidx.compose.material3.icons.filled.Favorite
import androidx.compose.material3.icons.filled.Person

actual sealed class NavigationItem actual constructor(
    actual val title: String,
    actual val icon: ImageVector
) {
    actual object Home : NavigationItem("Home", Icons.Filled.Home)
    actual object Marketplace : NavigationItem("Marketplace", Icons.Filled.Star)   // fallback
    actual object Portfolio : NavigationItem("Portfolio", Icons.Filled.Favorite)   // fallback
    actual object Profile : NavigationItem("Profile", Icons.Filled.Person)

    actual companion object {
        actual val items = listOf(Home, Marketplace, Portfolio, Profile)
    }
}
