package com.gibson.spica.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun PlatformNavContainer(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit,
    content: @Composable () -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        PlatformNavBar(selectedItem, onItemSelected)
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}
