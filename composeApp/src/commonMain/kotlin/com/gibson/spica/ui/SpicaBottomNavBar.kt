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
import androidx.compose.material3.Surface
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

// --- Data class for tab item ---
data class SpicaTab(val label: String, val icon: ImageVector)

// --- Colors ---
private val NavBarBackground = Color(0xFF1C1C1E)
private val SelectedGreen = Color(0xFFAEF359)
private val UnselectedGray = Color(0xFF333333)
private val SelectedIconTint = Color.Black
private val UnselectedIconTint = Color.White
private val TextColor = Color.White

@Composable
fun SpicaBottomNavBar(
    modifier: Modifier = Modifier,
    tabs: List<SpicaTab>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(66.dp) // Outer main pill height
            .padding(horizontal = 16.dp),
        color = Color.Transparent
    ) {
        // Outer pill background (no internal padding)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(50))
                .background(NavBarBackground),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
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
                        .fillMaxHeight(), // 👈 Fills the full height of nav bar
                    contentAlignment = Alignment.Center
                ) {
                    if (selected) {
                        // --- Selected pill ---
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(50))
                                .background(UnselectedGray.copy(alpha = 0.45f))
                                .padding(horizontal = 14.dp), // only horizontal padding now
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(SelectedGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.label,
                                    tint = SelectedIconTint,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = tab.label,
                                color = TextColor,
                                fontSize = 15.sp,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    } else {
                        // --- Unselected tab ---
                        Box(
                            modifier = Modifier
                                .size(58.dp)
                                .clip(CircleShape)
                                .background(UnselectedGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label,
                                tint = UnselectedIconTint,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun SpicaBottomNavBarPreview() {
    val tabs = listOf(
        SpicaTab("Home", Icons.Default.Home),
        SpicaTab("Portfolio", Icons.Default.Wallet),
        SpicaTab("Watchlist", Icons.Default.PieChart),
        SpicaTab("Markets", Icons.Default.Public)
    )
    var selectedIndex by remember { mutableStateOf(0) }

    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        SpicaBottomNavBar(
            tabs = tabs,
            selectedIndex = selectedIndex,
            onTabSelected = { selectedIndex = it }
        )
    }
}
