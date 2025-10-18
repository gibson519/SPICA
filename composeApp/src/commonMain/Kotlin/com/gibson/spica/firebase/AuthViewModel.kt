package com.gibson.spica.auth

import androidx.compose.runtime.*
import com.gibson.spica.firebase.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel {
    private val repository = AuthRepository()

    var authState by mutableStateOf<AuthResult?>(null)
        private set

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authState = repository.signUp(email, password)
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authState = repository.signIn(email, password)
        }
    }

    suspend fun sendEmailVerification() {
        repository.sendEmailVerification()
    }

    suspend fun reloadUser(): AuthResult {
        return repository.reloadUser().also {
            authState = it
        }
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut()
            authState = null
        }
    }
}
