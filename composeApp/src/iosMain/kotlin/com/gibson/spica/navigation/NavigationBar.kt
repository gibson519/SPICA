package com.gibson.spica.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
actual fun NavigationBar(
    selectedItem: NavigationItem,
    onItemSelected: (NavigationItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.weight(1f)) {
            NavigationHost(selectedItem)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem.items.forEach { item ->
                val isSelected = item == selectedItem

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onItemSelected(item) }
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color(0xFF00C853) else Color.Gray),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = if (isSelected) Color.Black else Color.White.copy(alpha = 0.6f)
                        )
                    }

                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFF00C853))
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.title,
                                color = Color.White,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
