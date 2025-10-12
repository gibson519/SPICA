package com.gibson.spica.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.gibson.spica.navigation.Screen

@Composable
actual fun AppNavBar(
    currentRoute: String?,
    onItemClick: (route: String) -> Unit
) {
    SpicaSideNav(
        modifier = Modifier
            .fillMaxHeight()
            .width(92.dp)
            .align(Alignment.CenterStart),
        tabs = DefaultSpicaTabs,
        selectedIndex = when (currentRoute) {
            Screen.Home.route -> 0
            Screen.Portfolio.route -> 1
            Screen.Watchlist.route -> 2
            Screen.Markets.route -> 3
            else -> 0
        },
        onTabSelected = { index ->
            onItemClick(
                when (index) {
                    0 -> Screen.Home.route
                    1 -> Screen.Portfolio.route
                    2 -> Screen.Watchlist.route
                    3 -> Screen.Markets.route
                    else -> Screen.Home.route
                }
            )
        }
    )
}
