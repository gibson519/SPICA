package com.gibson.spica.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Shared colors from bottom nav ---
private val NavBarBackground = Color(0xFF1C1C1E)
private val SelectedGreen = Color(0xFFAEF359)
private val UnselectedGray = Color(0xFF333333)
private val SelectedIconTint = Color.Black
private val UnselectedIconTint = Color.White
private val TextColor = Color.White

// --- Data class reused ---
data class SpicaSideTab(val label: String, val icon: ImageVector)

@Composable
fun SpicaSideNav(
    modifier: Modifier = Modifier,
    tabs: List<SpicaSideTab>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .width(84.dp) // Compact side width
            .fillMaxHeight()
            .background(NavBarBackground)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            tabs.forEachIndexed { idx, tab ->
                val selected = idx == selectedIndex

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onTabSelected(idx) }
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(if (selected) SelectedGreen else UnselectedGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label,
                                tint = if (selected) SelectedIconTint else UnselectedIconTint,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = tab.label,
                            color = TextColor.copy(alpha = if (selected) 1f else 0.6f),
                            fontSize = 13.sp,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun SpicaSideNavPreview() {
    val tabs = listOf(
        SpicaSideTab("Home", Icons.Default.Home),
        SpicaSideTab("Portfolio", Icons.Default.Wallet),
        SpicaSideTab("Watchlist", Icons.Default.PieChart),
        SpicaSideTab("Markets", Icons.Default.Public)
    )
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
        SpicaSideNav(
            tabs = tabs,
            selectedIndex = selectedIndex,
            onTabSelected = { selectedIndex = it }
        )
    }
}
