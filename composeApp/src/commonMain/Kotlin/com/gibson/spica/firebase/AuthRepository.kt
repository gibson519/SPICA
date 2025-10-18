package com.gibson.spica.auth

import com.gibson.spica.firebase.FirebaseAuthService
import com.gibson.spica.auth.AuthResult

class AuthRepository(
    private val firebaseAuth: FirebaseAuthService = FirebaseAuthService()
) {
    suspend fun signUp(email: String, password: String): AuthResult =
        firebaseAuth.signUp(email, password)

    suspend fun signIn(email: String, password: String): AuthResult =
        firebaseAuth.signIn(email, password)

    suspend fun sendEmailVerification() = firebaseAuth.sendEmailVerification()
    suspend fun reloadUser() = firebaseAuth.reloadUser()
    suspend fun signOut() = firebaseAuth.signOut()

    fun currentUserId() = firebaseAuth.currentUserId()
    fun isEmailVerified() = firebaseAuth.isEmailVerified()
}
