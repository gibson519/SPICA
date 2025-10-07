package com.gibson.spica.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding

// --- constants ---
private val NavBarHeight = 70.dp
private val OuterPadding = 5.dp
private val InnerPadding = 5.dp
private val TabHeight = 56.dp // uniform height for all tabs

private val NavBarBackground = Color(0xFF101012)
private val UnselectedBg = Color.Gray.copy(alpha = 0.45f)
private val UnselectedIconTint = Color.White
private val SelectedPillBg = Color.Gray.copy(alpha = 0.45f)
private val SelectedCircleGreen = Color(0xFF2ECC71)
private val SelectedIconTint = Color.Black
private val SelectedTextColor = Color.White

data class SpicaTab(val label: String, val icon: ImageVector)

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
            .height(NavBarHeight)
            .padding(horizontal = OuterPadding, bottom = 8.dp)
            // ✅ Apply only bottom insets, not height inflation
            .windowInsetsPadding(
                WindowInsets.navigationBars.only(WindowInsetsSides.Bottom)
            ),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(NavBarBackground)
                .padding(horizontal = InnerPadding, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { idx, tab ->
                val selected = idx == selectedIndex

                // Fixed proportional widths (no animation)
                val weight = if (selected) 15f else 8f

                Box(
                    modifier = Modifier
                        .weight(weight)
                        .height(TabHeight)
                        .clickable { onTabSelected(idx) },
                    contentAlignment = Alignment.Center
                ) {
                    if (selected) {
                        // --- Selected pill content ---
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(TabHeight)
                                .clip(RoundedCornerShape(50))
                                .background(SelectedPillBg)
                                .padding(start = 8.dp, end = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(SelectedCircleGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.label,
                                    tint = SelectedIconTint,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = tab.label,
                                color = SelectedTextColor,
                                fontSize = 13.sp,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    } else {
                        // --- Unselected icon only ---
                        Box(
                            modifier = Modifier
                                .size(TabHeight)
                                .clip(CircleShape)
                                .background(UnselectedBg),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label,
                                tint = UnselectedIconTint,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
