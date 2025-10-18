package com.gibson.spica.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object AuthRepository {
    private val auth = FirebaseAuth.getInstance()

    private val _currentUser = MutableStateFlow(auth.currentUser)
    val currentUser = _currentUser.asStateFlow()

    init {
        // Listen for login/logout changes
        auth.addAuthStateListener {
            _currentUser.value = it.currentUser
        }
    }

    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    suspend fun login(email: String, password: String) = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
        auth.currentUser
    }

    suspend fun signUp(email: String, password: String) = runCatching {
        auth.createUserWithEmailAndPassword(email, password).await()
        auth.currentUser
    }

    fun logout() {
        auth.signOut()
    }
}
