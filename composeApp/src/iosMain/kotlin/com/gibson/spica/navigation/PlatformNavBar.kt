package com.gibson.spica.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF111111))
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavItem.items.forEach { item ->
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = if (item == selectedItem) Color(0xFF00C853) else Color.White.copy(0.6f),
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onItemSelected(item) }
            )
        }
    }
}
