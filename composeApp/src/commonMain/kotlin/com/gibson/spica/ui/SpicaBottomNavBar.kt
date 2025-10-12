package com.gibson.spica.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

// --- Sizing Constants ---
private val NavBarHeight = 80.dp
private val NavBarCorner = 40.dp
private val NavBarHorizontalPadding = 5.dp
private val UnselectedTabSize = 68.dp
private val UnselectedIconSize = 24.dp
private val SelectedPillHeight = 70.dp
private val SelectedPillWidth = 150.dp
private val SelectedPillCorner = 32.dp
private val SelectedIconCircle = 50.dp
private val SelectedIconSize = 24.dp
private val SelectedIconTextGap = 6.dp
private val SelectedTextSize = 14.sp

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
            .padding(horizontal = NavBarHorizontalPadding),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(NavBarCorner))
                .background(NavBarBackground),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { idx, tab ->
                val selected = idx == selectedIndex

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(SelectedPillCorner))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onTabSelected(idx) }
                        .height(SelectedPillHeight),
                    contentAlignment = Alignment.Center
                ) {
                    if (selected) {
                        Row(
                            modifier = Modifier
                                .width(SelectedPillWidth)
                                .height(SelectedPillHeight)
                                .clip(RoundedCornerShape(SelectedPillCorner))
                                .background(UnselectedGray.copy(alpha = 0.45f))
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(SelectedIconCircle)
                                    .clip(CircleShape)
                                    .background(SelectedGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.label,
                                    tint = SelectedIconTint,
                                    modifier = Modifier.size(SelectedIconSize)
                                )
                            }

                            Spacer(modifier = Modifier.width(SelectedIconTextGap))

                            Text(
                                text = tab.label,
                                color = TextColor,
                                fontSize = SelectedTextSize,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(UnselectedTabSize)
                                .clip(CircleShape)
                                .background(UnselectedGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label,
                                tint = UnselectedIconTint,
                                modifier = Modifier.size(UnselectedIconSize)
                            )
                        }
                    }
                }
            }
        }
    }
}
