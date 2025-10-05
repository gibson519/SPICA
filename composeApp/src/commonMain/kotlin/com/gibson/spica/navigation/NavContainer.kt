package com.gibson.spica.navigation

import androidx.compose.runtime.Composable
import com.gibson.spica.navigation.NavItem

@Composable
expect fun PlatformNavContainer(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit,
    content: @Composable () -> Unit
)
