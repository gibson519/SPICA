package com.gibson.spica.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val state: StateFlow<AuthUiState> = _state

    fun signUp(email: String, password: String) {
        scope.launch {
            _state.value = AuthUiState.Loading
            try {
                val result = repository.signUp(email, password)
                _state.value = AuthUiState.Success(result)
            } catch (e: Exception) {
                _state.value = AuthUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun signIn(email: String, password: String) {
        scope.launch {
            _state.value = AuthUiState.Loading
            try {
                val result = repository.signIn(email, password)
                _state.value = AuthUiState.Success(result)
            } catch (e: Exception) {
                _state.value = AuthUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun signOut() {
        scope.launch {
            repository.signOut()
            _state.value = AuthUiState.SignedOut
        }
    }
}

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val result: AuthResult) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
    object SignedOut : AuthUiState()
}
