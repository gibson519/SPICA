package com.gibson.spica.ui

import androidx.compose.runtime.Composable

/**
 * Platform-specific navigation bar (expect/actual).
 * - Android/iOS → Bottom Nav Bar
 * - Desktop/Web → Side Nav Bar
 */
@Composable
expect fun AppNavBar(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
)
