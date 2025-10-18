package com.gibson.spica.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.spica.auth.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun AccountSetupScreen(
    viewModel: AuthViewModel,
    onContinue: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var message by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Verify Your Email",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "A verification link has been sent to your email address. Please verify your email before continuing.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.sendEmailVerification()
                        message = "Verification email sent!"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Resend Verification Email")
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        val result = viewModel.reloadUser()
                        if (result.isVerified) {
                            onContinue()
                        } else {
                            message = "Email not verified yet. Please check your inbox."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue")
            }

            message?.let {
                Spacer(Modifier.height(16.dp))
                Text(it, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}
