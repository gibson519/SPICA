package com.gibson.spica.navigation

import androidx.compose.runtime.Composable

@Composable
expect fun PlatformNavBar(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit
)
