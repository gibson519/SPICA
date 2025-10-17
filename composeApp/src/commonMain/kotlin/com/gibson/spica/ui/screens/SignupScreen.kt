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
fun SignupScreen() {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("SPICA — Sign Up", style = MaterialTheme.typography.titleLarge)

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    loading = true
                    scope.launch {
                        val result = AuthRepository.signUp(email, password)
                        loading = false
                        result.onSuccess {
                            message = "Account created! Verify your email."
                            Router.navigate(Screen.EmailVerify.route)
                        }.onFailure {
                            message = it.message
                        }
                    }
                },
                enabled = !loading
            ) {
                Text(if (loading) "Creating..." else "Sign Up")
            }

            Spacer(Modifier.height(12.dp))
            TextButton(onClick = { Router.navigate(Screen.Login.route) }) {
                Text("Already have an account? Log in")
            }

            message?.let {
                Spacer(Modifier.height(10.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
