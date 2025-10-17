package com.gibson.spica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.spica.data.FirebaseManager
import com.gibson.spica.navigation.Router
import com.gibson.spica.navigation.Screen
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun AccountSetupScreen() {
    val scope = rememberCoroutineScope()
    var username by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Account Setup", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(20.dp))

            OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(value = country, onValueChange = { country = it }, label = { Text("Country") })
            Spacer(Modifier.height(20.dp))

            Button(onClick = {
                val user = FirebaseManager.auth.currentUser ?: return@Button
                loading = true
                scope.launch {
                    try {
                        val userData = mapOf(
                            "uid" to user.uid,
                            "email" to user.email,
                            "username" to username,
                            "country" to country,
                            "createdAt" to System.currentTimeMillis()
                        )
                        FirebaseManager.firestore.collection("users").document(user.uid).set(userData).await()
                        Router.navigate(Screen.Home.route)
                    } catch (e: Exception) {
                        message = e.message
                    } finally {
                        loading = false
                    }
                }
            }, enabled = !loading) {
                Text(if (loading) "Saving..." else "Finish Setup")
            }

            message?.let {
                Spacer(Modifier.height(10.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
