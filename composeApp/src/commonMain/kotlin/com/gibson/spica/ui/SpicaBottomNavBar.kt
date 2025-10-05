package com.gibson.spica.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val NavBarHeight = 64.dp
private val OuterPadding = 12.dp
private val UnselectedCircleSize = 44.dp
private val SelectedInnerPillHeight = 40.dp
private val SelectedInnerPillHorizontalPadding = 12.dp

private val NavBarBackground = Color(0xCC0B0B0D) // semi-transparent dark
private val UnselectedBg = Color(0xFF6E6E73) // grey
private val UnselectedIconTint = Color.White.copy(alpha = 0.62f)
private val SelectedCircleGreen = Color(0xFF2ECC71) // green
private val SelectedIconTint = Color.Black
private val SelectedTextColor = Color.White

data class SpicaTab(val label: String, val icon: ImageVector)

/**
 * Pill-shaped bottom navigation bar with a spotlight inner pill for the selected tab.
 *
 * tabs must be supplied from platform/common sources; the code expects ImageVector icons.
 */
@Composable
fun SpicaBottomNavBar(
    modifier: Modifier = Modifier,
    tabs: List<SpicaTab>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Surface(
        modifier = modifier
            .height(NavBarHeight)
            .padding(horizontal = OuterPadding)
            .fillMaxWidth(),
        color = Color.Transparent
    ) {
        // Outer pill
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(NavBarBackground)
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .animateContentSize(animationSpec = tween(durationMillis = 220)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { idx, tab ->
                val selected = idx == selectedIndex

                val targetWidth: Dp = if (selected) {
                    SelectedInnerPillHeight + SelectedInnerPillHorizontalPadding * 2 + (tab.label.length * 6).dp
                } else {
                    UnselectedCircleSize
                }

                // <-- IMPORTANT: 'getValue' operator is required for 'by' here.
                // We import androidx.compose.runtime.getValue at top; this resolves the
                // "property delegates must have 'getValue(Nothing?, kproperty0<*>)'" error.
                val animatedWidth by animateDpAsState(targetValue = targetWidth, animationSpec = tween(durationMillis = 240))

                Box(
                    modifier = Modifier
                        .width(animatedWidth)
                        .height(SelectedInnerPillHeight)
                        .clip(RoundedCornerShape(50))
                        .clickable { onTabSelected(idx) }
                        .padding(horizontal = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (selected) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .animateContentSize(animationSpec = tween(durationMillis = 220))
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(SelectedCircleGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.label,
                                    tint = SelectedIconTint,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = tab.label,
                                color = SelectedTextColor,
                                fontSize = 14.sp,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(UnselectedCircleSize)
                                .clip(CircleShape)
                                .background(UnselectedBg),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label,
                                tint = UnselectedIconTint,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                if (idx != tabs.lastIndex) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}
