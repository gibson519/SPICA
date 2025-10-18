package com.gibson.spica.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

// --- Common tab data model ---
data class SpicaTab(val label: String, val icon: ImageVector)

// --- Expect declaration for multiplatform NavBar ---
@Composable
expect fun NavBar(
    modifier: Modifier = Modifier,
    tabs: List<SpicaTab>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
)
