package com.gibson.spica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.spica.data.AuthRepository
import com.gibson.spica.navigation.Router
import com.gibson.spica.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun EmailVerifyScreen() {
    val scope = rememberCoroutineScope()
    val user = AuthRepository.currentUser
    var message by remember { mutableStateOf("A verification link has been sent to your email.") }
    var loading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Verify Your Email", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(12.dp))
            Text(message)
            Spacer(Modifier.height(20.dp))

            Button(onClick = {
                loading = true
                scope.launch {
                    try {
                        user?.sendEmailVerification()
                        message = "Verification link re-sent."
                    } catch (e: Exception) {
                        message = e.message ?: "Error sending verification link."
                    } finally {
                        loading = false
                    }
                }
            }, enabled = !loading) {
                Text(if (loading) "Sending..." else "Resend Email")
            }

            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                user?.reload()
                if (user?.isEmailVerified == true) {
                    Router.navigate(Screen.AccountSetup.route)
                } else {
                    message = "Email not verified yet. Try again after confirming."
                }
            }) {
                Text("Continue")
            }

            Spacer(Modifier.height(12.dp))
            TextButton(onClick = {
                AuthRepository.logout()
                Router.navigate(Screen.Login.route)
            }) {
                Text("Logout")
            }
        }
    }
}
