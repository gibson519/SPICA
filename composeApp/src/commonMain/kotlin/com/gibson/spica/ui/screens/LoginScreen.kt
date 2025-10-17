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
fun LoginScreen() {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
            Text("Login", style = MaterialTheme.typography.titleLarge)

            Spacer(Modifier.height(24.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    scope.launch {
                        loading = true
                        val result = FirebaseHelper.login(email, password)
                        loading = false
                        result.onSuccess {
                            Router.navigate(Screen.Home.route)
                        }.onFailure {
                            message = it.message
                        }
                    }
                },
                enabled = !loading && email.isNotEmpty() && password.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (loading) "Logging in..." else "Login")
            }

            message?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(20.dp))
            TextButton(onClick = { Router.navigate(Screen.Signup.route) }) {
                Text("Don’t have an account? Sign up")
            }
        }
    }
}
