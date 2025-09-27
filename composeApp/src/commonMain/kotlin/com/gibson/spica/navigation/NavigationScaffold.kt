package com.gibson.spica.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class NavigationLayout { Bottom, Side }

@Composable
fun NavigationScaffold(
    layout: NavigationLayout,
    selectedItem: NavigationItem,
    onItemSelected: (NavigationItem) -> Unit,
    items: List<NavigationItem> = NavigationItem.items
) {
    when (layout) {
        NavigationLayout.Bottom -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Main content
                Box(modifier = Modifier.weight(1f)) {
                    NavigationHost(selectedItem)
                }

                // Bottom nav row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEach { item ->
                        NavigationItemView(
                            item = item,
                            isSelected = item == selectedItem,
                            onClick = { onItemSelected(item) }
                        )
                    }
                }
            }
        }

        NavigationLayout.Side -> {
            Row(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                        .padding(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items.forEach { item ->
                        NavigationItemView(
                            item = item,
                            isSelected = item == selectedItem,
                            onClick = { onItemSelected(item) }
                        )
                    }
                }

                // Main content
                Box(modifier = Modifier.weight(1f)) {
                    NavigationHost(selectedItem)
                }
            }
        }
    }
}
