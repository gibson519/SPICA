package com.gibson.spica.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

// --- Data class for a tab item ---
data class SpicaTab(val label: String, val icon: ImageVector)

// --- More accurate colors based on the design ---
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
            .height(72.dp) // A bit more height for padding
            .padding(horizontal = 16.dp, vertical = 8.dp), // Outer padding
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(50)) // Pill shape
                .background(NavBarBackground)
                .padding(horizontal = 8.dp), // Inner padding
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { idx, tab ->
                val selected = idx == selectedIndex

                // Animate the weight for smooth expansion/shrinking
                val weight by animateFloatAsState(
                    targetValue = if (selected) 3.5f else 1f,
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = FastOutSlowInEasing
                    )
                )

                // Animate colors for the icon and its background
                val iconBgColor by animateColorAsState(
                    targetValue = if (selected) SelectedGreen else UnselectedGray,
                    animationSpec = tween(400)
                )

                val iconTintColor by animateColorAsState(
                    targetValue = if (selected) SelectedIconTint else UnselectedIconTint,
                    animationSpec = tween(400)
                )

                // Each item is a Box with an animated weight
                Box(
                    modifier = Modifier
                        .weight(weight)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(50))
                        .clickable(
                            interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                            indication = null // No ripple effect
                        ) { onTabSelected(idx) },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // The icon in its circular, color-animated background
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(iconBgColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label,
                                tint = iconTintColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // The text label that animates in and out
                        AnimatedVisibility(
                            visible = selected,
                            enter = fadeIn(animationSpec = tween(200, delayMillis = 200)) +
                                    expandHorizontally(animationSpec = tween(400, easing = FastOutSlowInEasing)),
                            exit = fadeOut(animationSpec = tween(200)) +
                                   shrinkHorizontally(animationSpec = tween(400, easing = FastOutSlowInEasing))
                        ) {
                            Text(
                                text = tab.label,
                                color = TextColor,
                                fontSize = 14.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


/* --- A simple preview function to test the component ---
@Preview(showBackground = true, backgroundColor = 0xFF333333)
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
*/
