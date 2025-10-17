package com.gibson.spica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.spica.firebase.FirebaseHelper
import com.gibson.spica.navigation.Router
import com.gibson.spica.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun EmailVerifyScreen() {
    val scope = rememberCoroutineScope()
    var message by remember { mutableStateOf<String?>(null) }
    var checking by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
            Text("Verify Your Email", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(20.dp))
            Text(
                "A verification link has been sent to your email. Please verify before continuing.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    scope.launch {
                        checking = true
                        val user = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
                        user?.reload()?.addOnSuccessListener {
                            checking = false
                            if (user.isEmailVerified) {
                                Router.navigate(Screen.AccountSetup.route)
                            } else {
                                message = "Email not verified yet."
                            }
                        }?.addOnFailureListener {
                            checking = false
                            message = it.message
                        }
                    }
                },
                enabled = !checking
            ) {
                Text(if (checking) "Checking..." else "Check Verification")
            }

            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = {
                    scope.launch {
                        FirebaseHelper.sendEmailVerification()
                        message = "Verification email resent!"
                    }
                }
            ) {
                Text("Resend Email")
            }

            message?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
