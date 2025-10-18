package com.gibson.spica.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.spica.auth.AuthRepository

@Composable
fun AccountSetupScreen(
    repository: AuthRepository = AuthRepository(),
    onComplete: () -> Unit
) {
    var verified by remember { mutableStateOf(repository.isEmailVerified()) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!verified) {
                Text(
                    "Please verify your email before continuing.",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = { repository.sendEmailVerification() }) {
                    Text("Resend Verification Email")
                }
                Spacer(Modifier.height(16.dp))
                Button(onClick = {
                    repository.reloadUser()
                    verified = repository.isEmailVerified()
                    if (verified) onComplete()
                }) {
                    Text("I’ve Verified My Email")
                }
            } else {
                Text("Email verified ✅", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(24.dp))
                Button(onClick = onComplete) {
                    Text("Continue to Home")
                }
            }
        }
    }
}
