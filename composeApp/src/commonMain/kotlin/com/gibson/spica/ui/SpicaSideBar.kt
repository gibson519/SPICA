package com.gibson.spica.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

// ✅ Data class for side nav tab
data class SpicaSideTab(val label: String, val icon: ImageVector)

// ✅ Colors and dimensions
private val SideNavBackground = Color(0xFF1C1C1E)
private val SelectedGreen = Color(0xFFAEF359)
private val UnselectedIconTint = Color.White.copy(alpha = 0.7f)
private val SelectedIconTint = Color.Black

private val SideNavWidth = 35.dp
private val SideNavCorner = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp) // ✅ reduced rounding
private val SelectedCircleSize = 35.dp
private val IconSize = 24.dp
private val TopPadding = 5.dp
private val BottomPadding = 20.dp
private val IconSpacing = 18.dp

@Composable
fun SpicaSideNav(
    modifier: Modifier = Modifier,
    tabs: List<SpicaSideTab>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(SideNavWidth)
            .clip(SideNavCorner)
            .background(SideNavBackground)
            .padding(top = TopPadding, bottom = BottomPadding)
            .offset(x = 0.dp), // ✅ ensure flush left edge
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Top section: icons ---
        Column(
            verticalArrangement = Arrangement.spacedBy(IconSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            tabs.forEachIndexed { idx, tab ->
                val selected = idx == selectedIndex
                Box(
                    modifier = Modifier
                        .size(SelectedCircleSize)
                        .clip(CircleShape)
                        .background(if (selected) SelectedGreen else Color.Transparent)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onTabSelected(idx) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                        tint = if (selected) SelectedIconTint else UnselectedIconTint,
                        modifier = Modifier.size(IconSize)
                    )
                }
            }
        }

        // --- Bottom: Settings icon ---
        Box(
            modifier = Modifier
                .size(SelectedCircleSize)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { /* TODO: Handle settings click */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = UnselectedIconTint,
                modifier = Modifier.size(IconSize)
            )
        }
    }
}
