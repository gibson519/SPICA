package com.gibson.spica.navigation

import androidx.compose.runtime.Composable

@Composable
expect fun NavigationBar(
    selectedItem: NavigationItem,
    onItemSelected: (NavigationItem) -> Unit
)
