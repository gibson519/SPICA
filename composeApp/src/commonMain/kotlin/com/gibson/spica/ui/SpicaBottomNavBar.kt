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

// --- constants ---
private val NavBarHeight = 90.dp
private val OuterPadding = 12.dp
private val InnerPadding = 10.dp
private val TabHeight = 56.dp
private val TabSpacing = 12.dp // ✅ consistent spacing between and at edges

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
            .navigationBarsPadding()
            .height(NavBarHeight)
            .padding(horizontal = OuterPadding, vertical = 6.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(NavBarBackground)
                .padding(horizontal = InnerPadding, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(TabSpacing, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { idx, tab ->
                val selected = idx == selectedIndex

                // Each slot has equal fixed space — content differs per tab
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(TabHeight)
                        .clickable { onTabSelected(idx) },
                    contentAlignment = Alignment.Center
                ) {
                    if (selected) {
                        // --- Selected pill content ---
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(SelectedPillBg)
                                .padding(horizontal = 10.dp)
                                .height(TabHeight)
                                .fillMaxWidth(fraction = 0.9f), // keeps internal size smaller but centered
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(SelectedCircleGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.label,
                                    tint = SelectedIconTint,
                                    modifier = Modifier.size(25.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = tab.label,
                                color = SelectedTextColor,
                                fontSize = 13.sp,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    } else {
                        // --- Unselected circular tab ---
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
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
