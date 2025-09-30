package com.gibson.spica.navigation

import androidx.compose.ui.graphics.vector.ImageVector

expect sealed class NavigationItem(title: String, icon: ImageVector) {
    val title: String
    val icon: ImageVector

    object Home : NavigationItem
    object Marketplace : NavigationItem
    object Portfolio : NavigationItem
    object Profile : NavigationItem

    companion object {
        val items: List<NavigationItem>
    }
}
