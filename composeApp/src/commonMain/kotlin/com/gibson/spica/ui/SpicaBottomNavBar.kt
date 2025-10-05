package com.gibson.spica.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val NavBarHeight = 80.dp
private val OuterPadding = 14.dp
private val UnselectedCircleSize = 77.dp
private val SelectedInnerPillHeight = 77.dp
private val SelectedInnerPillHorizontalPadding = 18.dp

private val NavBarBackground = Color(0xFF101012)           // true dark pill
private val UnselectedBg = Color(0xFF1B1B1E)               // darker circles
private val UnselectedIconTint = Color.White.copy(alpha = 0.45f)
private val SelectedPillBg = Color(0xFF18181B)             // dark inner pill background
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
            .padding(horizontal = OuterPadding, vertical = 10.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(NavBarBackground)
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .animateContentSize(animationSpec = tween(200)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { idx, tab ->
                val selected = idx == selectedIndex

                val targetWidth: Dp = if (selected) {
                    SelectedInnerPillHeight + SelectedInnerPillHorizontalPadding * 2 + (tab.label.length * 5.5).dp
                } else {
                    UnselectedCircleSize
                }

                val animatedWidth by animateDpAsState(
                    targetValue = targetWidth,
                    animationSpec = tween(durationMillis = 200)
                )

                Box(
                    modifier = Modifier
                        .width(animatedWidth)
                        .height(SelectedInnerPillHeight)
                        .clip(RoundedCornerShape(50))
                        .background(if (selected) SelectedPillBg else Color.Transparent)
                        .clickable { onTabSelected(idx) }
                        .padding(horizontal = 6.dp),
                    contentAlignment = Alignment.Center // OK to keep center; selected Row will fill width
                ) {
                    if (selected) {
                        // <-- Key change: fill the entire pill and start-align contents
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp) // controls how far the green circle sits from the pill left edge
                                .animateContentSize(animationSpec = tween(180)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(SelectedCircleGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.label,
                                    tint = SelectedIconTint,
                                    modifier = Modifier.size(18.dp)
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
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                if (idx != tabs.lastIndex) {
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }
        }
    }
}
