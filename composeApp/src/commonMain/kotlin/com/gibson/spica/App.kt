package com.gibson.spica

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.gibson.spica.ui.*

/**

Root app composable (commonMain) without using Modifier.weight to avoid internal Row/Column

implementation visibility issues on some MPP targets.
*/
@Composable
fun App() {
MaterialTheme {
Surface(modifier = Modifier.fillMaxSize()) {
val selectedState = remember { mutableStateOf(0) }

// Use a Box so we can align the bottom nav and give the content area a bottom padding  
     // instead of relying on Modifier.weight (which can cause visibility errors in MPP).  
     Box(modifier = Modifier.fillMaxSize()) {  
         // Reserve space at the bottom for the nav by padding the content column.  
         // The value 88.dp is a conservative allowance: nav height (64) + paddings.  
         Column(modifier = Modifier  
             .fillMaxSize()  
             .padding(bottom = 88.dp)) {  

             when (selectedState.value) {  
                 0 -> com.gibson.spica.ui.HomeScreen()  
                 1 -> com.gibson.spica.ui.PortfolioScreen()  
                 2 -> com.gibson.spica.ui.WatchlistScreen()  
                 3 -> com.gibson.spica.ui.MarketsScreen()  
                 else -> com.gibson.spica.ui.HomeScreen()  
             }  
         }  

         // Bottom nav aligned to the bottom center  
         SpicaBottomNavBar(  
             modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 12.dp),  
             tabs = DefaultSpicaTabs,  
             selectedIndex = selectedState.value,  
             onTabSelected = { selectedState.value = it }  
         )  
     }  
 }

}
}


App.kt

You already have the spicabottomnavbar

