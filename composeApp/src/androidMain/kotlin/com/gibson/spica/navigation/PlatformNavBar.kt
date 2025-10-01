package com.gibson.spica.navigation

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformNavBar(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit
) {
    BottomNavBar(selectedItem, onItemSelected)
}
