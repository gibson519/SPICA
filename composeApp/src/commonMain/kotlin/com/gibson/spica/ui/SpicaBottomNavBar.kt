package com.gibson.spica.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
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

// --- Colors matching the mockup ---
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
    val totalTabs = tabs.size
    val expandedWeight = 1.5f
    val collapsedWeight = remember(expandedWeight, totalTabs) {
        (totalTabs.toFloat() - expandedWeight) / (totalTabs - 1)
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .padding(bottom = 8.dp),
        color = Color.Transparent,
        shadowElevation = 6.dp // subtle floating effect
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(50))
                .background(NavBarBackground)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { idx, tab ->
                val selected = idx == selectedIndex

                // Smooth proportional resizing animation
                val targetWeight = if (selected) expandedWeight else collapsedWeight
                val weight by animateFloatAsState(
                    targetValue = targetWeight,
                    animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
                )

                // Animated background + icon tint
                val iconBgColor by animateColorAsState(
                    targetValue = if (selected) SelectedGreen else UnselectedGray,
                    animationSpec = tween(300)
                )
                val iconTintColor by animateColorAsState(
                    targetValue = if (selected) SelectedIconTint else UnselectedIconTint,
                    animationSpec = tween(300)
                )

                Box(
                    modifier = Modifier
                        .weight(weight)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(50))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onTabSelected(idx) },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 12.dp)
                    ) {
                        // --- Green circle for icon ---
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

                        // --- Label appears only when selected ---
                        AnimatedVisibility(
                            visible = selected,
                            enter = fadeIn(animationSpec = tween(150, delayMillis = 100)) +
                                    expandHorizontally(
                                        animationSpec = tween(300, easing = FastOutSlowInEasing)
                                    ),
                            exit = fadeOut(animationSpec = tween(150)) +
                                    shrinkHorizontally(
                                        animationSpec = tween(300, easing = FastOutSlowInEasing)
                                    )
                        ) {
                            Text(
                                text = tab.label,
                                color = TextColor,
                                fontSize = 14.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
/*
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
}*/
