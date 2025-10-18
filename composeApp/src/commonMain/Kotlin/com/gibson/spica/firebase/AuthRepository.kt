package com.gibson.spica.firebase

import com.gibson.spica.auth.AuthResult

/**
 * Cross-platform Firebase authentication interface.
 */
expect class AuthRepository() {
    suspend fun signUp(email: String, password: String): AuthResult
    suspend fun signIn(email: String, password: String): AuthResult
    suspend fun sendEmailVerification()
    suspend fun reloadUser(): AuthResult
    suspend fun signOut()
}
