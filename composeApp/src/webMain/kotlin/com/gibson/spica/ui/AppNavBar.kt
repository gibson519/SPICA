package com.gibson.spica.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Desktop/Web actual implementation:
 * Always shows side nav (right side for desktop, left for web if you want).
 */
@Composable
actual fun AppNavBar(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SpicaSideNav(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp),
            tabs = DefaultSpicaTabs,
            selectedIndex = selectedIndex,
            onTabSelected = onTabSelected
        )
    }
}
