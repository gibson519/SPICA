package com.gibson.spica.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
            .width(80.dp)
            .background(Color(0xFF1C1C1C)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        NavItem.items.forEach { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable { onItemSelected(item) }
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = if (item == selectedItem) Color(0xFF00C853) else Color.White.copy(0.6f)
                )
                Text(
                    text = item.title,
                    color = if (item == selectedItem) Color.White else Color.Gray
                )
            }
        }
    }
}
