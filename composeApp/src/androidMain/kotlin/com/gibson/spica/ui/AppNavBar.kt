package com.gibson.spica.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

@Composable
actual fun AppNavBar(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    // Use your already working SPICA bottom nav bar
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        SpicaBottomNavBar(
            modifier = Modifier.padding(bottom = 8.dp),
            tabs = DefaultSpicaTabs,
            selectedIndex = selectedIndex,
            onTabSelected = onTabSelected
        )
    }
}
