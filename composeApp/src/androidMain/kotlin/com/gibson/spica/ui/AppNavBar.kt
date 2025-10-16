package com.gibson.spica.ui

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

/**
 * Android actual implementation of AppNavBar.
 * - Uses bottom nav for Compact width (phones)
 * - Uses side nav for Medium/Expanded width (tablets and large screens)
 */
@Composable
actual fun AppNavBar(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity ?: return

    // ✅ Automatically determine the window size class
    val windowSizeClass = calculateWindowSizeClass(activity)
    val widthClass = windowSizeClass.widthSizeClass
    val isTabletOrLarger = widthClass != WindowWidthSizeClass.Compact

    Box(modifier = Modifier.fillMaxSize()) {
        if (isTabletOrLarger) {
            // ✅ Side navigation for tablets / large screens
            SpicaSideNav(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp),
                tabs = DefaultSpicaSideTabs,
                selectedIndex = selectedIndex,
                onTabSelected = onTabSelected
            )
        } else {
            // ✅ Bottom navigation for phones
            SpicaBottomNavBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                tabs = DefaultSpicaTabs,
                selectedIndex = selectedIndex,
                onTabSelected = onTabSelected
            )
        }
    }
}
