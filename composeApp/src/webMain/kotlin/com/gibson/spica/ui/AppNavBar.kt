package com.gibson.spica.ui

import androidx.compose.foundation.layout.*
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
    // Web layout: top navigation bar
    TopAppBar(
        title = { Text("SPICA") },
        actions = {
            tabs.forEachIndexed { index, tab ->
                IconButton(onClick = { onTabSelected(index) }) {
                    Icon(
                        tab.icon,
                        contentDescription = tab.label,
                        tint = if (index == selectedIndex)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    )
}
