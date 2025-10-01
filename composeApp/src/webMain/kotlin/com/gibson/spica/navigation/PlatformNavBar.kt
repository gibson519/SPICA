package com.gibson.spica.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
actual fun PlatformNavBar(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(70.dp)
            .background(Color(0xFF222222)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        NavItem.items.forEach { item ->
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = if (item == selectedItem) Color(0xFF00C853) else Color.White.copy(0.6f),
                modifier = Modifier
                    .padding(12.dp)
                    .size(28.dp)
                    .clickable { onItemSelected(item) }
            )
        }
    }
}
