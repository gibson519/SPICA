package com.gibson.spica.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Simple vertical side navigation that re-uses the project's SpicaTab + DefaultSpicaTabs.
 * Adjust spacing, width, and colors to taste.
 */
@Composable
fun SpicaSideNav(
    modifier: Modifier = Modifier,
    tabs: List<SpicaTab>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val navBg = Color(0xFF101012)
    val selectedBg = Color.White.copy(alpha = 0.12f)
    val unselectedTint = Color.White.copy(alpha = 0.75f)
    val selectedTint = Color.White

    Surface(
        modifier = modifier,
        color = navBg,
        tonalElevation = 2.dp,
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            tabs.forEachIndexed { index, tab ->
                val selected = index == selectedIndex
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp, horizontal = 8.dp)
                        .clickable { onTabSelected(index) }
                        .background(if (selected) selectedBg else Color.Transparent, RoundedCornerShape(8.dp))
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.label,
                            tint = if (selected) selectedTint else unselectedTint,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = tab.label,
                            color = if (selected) selectedTint else unselectedTint,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
