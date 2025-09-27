package com.gibson.spica

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.gibson.spica.navigation.NavigationItem
import com.gibson.spica.navigation.NavigationLayout
import com.gibson.spica.navigation.NavigationScaffold

@Composable
fun App() {
    val platform = getPlatform()
    var selectedItem by remember { mutableStateOf<NavigationItem>(NavigationItem.Home) }

    MaterialTheme {
        Surface {
            when (platform.type) {
                PlatformType.Android,
                PlatformType.iOS -> NavigationScaffold(
                    layout = NavigationLayout.Bottom,
                    selectedItem = selectedItem,
                    onItemSelected = { selectedItem = it }
                )

                PlatformType.Desktop,
                PlatformType.Web -> NavigationScaffold(
                    layout = NavigationLayout.Side,
                    selectedItem = selectedItem,
                    onItemSelected = { selectedItem = it }
                )
            }
        }
    }
}
