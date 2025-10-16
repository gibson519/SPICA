package com.gibson.spica.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

/**

Android/iOS actual implementation:

Uses SpicaBottomNavBar for small screens (phones)


Uses SpicaSideNav for tablets
*/
@Composable
actual fun AppNavBar(
selectedIndex: Int,
onTabSelected: (Int) -> Unit
) {
val screenWidth = LocalConfiguration.current.screenWidthDp
val isTablet = screenWidth > 600 // tablet breakpoint


Box(modifier = Modifier.fillMaxSize()) {
if (isTablet) {
// ✅ Side nav for large Android devices
SpicaSideNav(
modifier = Modifier
.align(Alignment.CenterStart)
.padding(start = 16.dp),
tabs = DefaultSpicaSideTabs,
selectedIndex = selectedIndex,
onTabSelected = onTabSelected
)
} else {
// ✅ Bottom nav for phones
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
