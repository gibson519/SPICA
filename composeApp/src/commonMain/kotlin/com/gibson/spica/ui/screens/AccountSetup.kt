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
fun AccountSetupScreen() {
    val scope = rememberCoroutineScope()
    var fullName by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
            Text("Account Setup", style = MaterialTheme.typography.titleLarge)

            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Bio (optional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    scope.launch {
                        loading = true
                        val email = FirebaseHelper.currentUserEmail() ?: ""
                        val uid = email.hashCode().toString()
                        val data = mapOf(
                            "uid" to uid,
                            "fullName" to fullName,
                            "bio" to bio,
                            "email" to email,
                            "createdAt" to System.currentTimeMillis()
                        )
                        val result = FirebaseHelper.saveUserProfile(uid, data)
                        loading = false
                        result.onSuccess {
                            message = "Profile saved!"
                            Router.navigate(Screen.PhoneVerify.route)
                        }.onFailure {
                            message = it.message
                        }
                    }
                },
                enabled = !loading && fullName.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (loading) "Saving..." else "Continue")
            }

            message?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
