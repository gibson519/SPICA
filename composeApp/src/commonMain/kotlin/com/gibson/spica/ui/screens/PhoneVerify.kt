package com.gibson.spica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.spica.navigation.Router
import com.gibson.spica.navigation.Screen

@Composable
fun PhoneVerifyScreen() {
    var phone by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }
    var step by remember { mutableStateOf(1) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
            Text("Phone Verification", style = MaterialTheme.typography.titleLarge)

            Spacer(Modifier.height(20.dp))
            when (step) {
                1 -> {
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = {
                            // TODO: Trigger Firebase PhoneAuthProvider here
                            step = 2
                            message = "Verification code sent!"
                        },
                        enabled = phone.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Send Code")
                    }
                }

                2 -> {
                    OutlinedTextField(
                        value = code,
                        onValueChange = { code = it },
                        label = { Text("Verification Code") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = {
                            // TODO: Verify code with Firebase
                            message = "Phone verified!"
                            Router.navigate(Screen.Home.route)
                        },
                        enabled = code.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Verify")
                    }
                }
            }

            message?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
