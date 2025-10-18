package com.gibson.spica.ui.auth

import androidx.compose.runtime.*
import com.gibson.spica.auth.AuthViewModel

enum class AuthDestination {
    LOGIN, SIGNUP, ACCOUNT_SETUP
}

@Composable
fun AuthNavHost(
    viewModel: AuthViewModel,
    onAuthFinished: () -> Unit
) {
    var current by remember { mutableStateOf(AuthDestination.LOGIN) }

    when (current) {
        AuthDestination.LOGIN -> LoginScreen(
            viewModel = viewModel,
            onLoginSuccess = { current = AuthDestination.ACCOUNT_SETUP },
            onNavigateToSignUp = { current = AuthDestination.SIGNUP }
        )

        AuthDestination.SIGNUP -> SignUpScreen(
            viewModel = viewModel,
            onSignUpSuccess = { current = AuthDestination.ACCOUNT_SETUP },
            onNavigateToLogin = { current = AuthDestination.LOGIN }
        )

        AuthDestination.ACCOUNT_SETUP -> AccountSetupScreen(
            onComplete = onAuthFinished
        )
    }
}
