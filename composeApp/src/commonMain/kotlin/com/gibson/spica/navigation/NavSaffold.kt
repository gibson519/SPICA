package com.gibson.spica.ui.navigation

import androidx.compose.runtime.Composable

@Composable
expect fun PlatformNavBar(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit
)
