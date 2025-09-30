package com.gibson.spica

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.gibson.spica.navigation.NavigationBar
import com.gibson.spica.navigation.NavigationItem

@Composable
fun App() {
    var selectedItem by remember { mutableStateOf<NavigationItem>(NavigationItem.Home) }

    MaterialTheme {
        Surface {
            NavigationBar(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        }
    }
}
