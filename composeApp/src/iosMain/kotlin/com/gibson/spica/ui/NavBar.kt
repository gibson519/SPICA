package com.gibson.spica.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun NavBar(
    modifier: Modifier,
    tabs: List<SpicaTab>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar(modifier = modifier) {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = { Icon(tab.icon, tab.label) },
                label = { Text(tab.label) },
                selected = index == selectedIndex,
                onClick = { onTabSelected(index) }
            )
        }
    }
}
