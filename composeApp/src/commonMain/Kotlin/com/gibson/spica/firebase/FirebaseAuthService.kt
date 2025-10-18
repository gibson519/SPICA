package com.gibson.spica.firebase

data class AuthResult(
    val userId: String?,
    val emailVerified: Boolean,
)

expect class FirebaseAuthService() {
    suspend fun signUp(email: String, password: String): AuthResult
    suspend fun signIn(email: String, password: String): AuthResult
    suspend fun sendEmailVerification()
    suspend fun reloadUser(): AuthResult
    suspend fun signOut()
    fun currentUserId(): String?
    fun isEmailVerified(): Boolean
}
