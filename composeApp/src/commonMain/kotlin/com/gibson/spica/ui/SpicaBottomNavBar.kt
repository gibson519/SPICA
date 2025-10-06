package com.gibson.spica.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- constants ---
private val NavBarHeight = 130.dp
private val OuterPadding = 5.dp
private val InnerPadding = 5.dp
private val InnerPillHeight = 128.dp

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
            .padding(horizontal = OuterPadding, vertical = 0.dp)
            .navigationBarsPadding(), // ✅ Prevents overlap with system nav bar
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

                // Animate proportional widths (15f for selected, 8f for unselected)
                val animatedWeight by animateFloatAsState(
                    targetValue = if (selected) 15f else 8f,
                    animationSpec = tween(durationMillis = 250)
                )

                Box(
                    modifier = Modifier
                        .weight(animatedWeight)
                        .height(InnerPillHeight)
                        .clickable { onTabSelected(idx) },
                    contentAlignment = Alignment.Center
                ) {
                    if (selected) {
                        // --- Selected pill content ---
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(50))
                                .background(SelectedPillBg)
                                .padding(start = 8.dp, end = 12.dp)
                                .animateContentSize(animationSpec = tween(200)),
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
                        // --- Unselected icon ---
                        Box(
                            modifier = Modifier
                                .size(56.dp)
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
