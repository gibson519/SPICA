package com.gibson.spica

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.gibson.spica.auth.AuthViewModel
import com.gibson.spica.ui.auth.AuthNavHost
import com.gibson.spica.ui.main.MainScreen

/**
 * Root composable of the SPICA app.
 * 
 * This function decides whether to show the authentication flow
 * or the main dashboard based on the user's login state.
 */
@Composable
fun App() {
    // Shared Auth ViewModel
    val viewModel = remember { AuthViewModel() }

    // Local state controlling navigation between Auth and Main
    var isLoggedIn by remember { mutableStateOf(false) }

    // Observe login state from ViewModel
    LaunchedEffect(viewModel) {
        viewModel.isUserLoggedIn.collect { loggedIn ->
            isLoggedIn = loggedIn
        }
    }

    MaterialTheme {
        if (isLoggedIn) {
            // 🔓 User is authenticated → Go to main dashboard
            MainScreen()
        } else {
            // 🔐 Show login/signup flow
            AuthNavHost(
                viewModel = viewModel,
                onAuthFinished = { isLoggedIn = true }
            )
        }
    }
}
