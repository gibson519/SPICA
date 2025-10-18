package com.gibson.spica.firebase

import com.gibson.spica.auth.AuthResult

actual class FirebaseAuthService actual constructor() {
    actual suspend fun signUp(email: String, password: String): AuthResult =
        throw NotImplementedError("Desktop Firebase Auth not yet supported")

    actual suspend fun signIn(email: String, password: String): AuthResult =
        throw NotImplementedError("Desktop Firebase Auth not yet supported")

    actual suspend fun sendEmailVerification() = Unit
    actual suspend fun reloadUser(): AuthResult = AuthResult(null, false)
    actual suspend fun signOut() = Unit
    actual fun currentUserId(): String? = null
    actual fun isEmailVerified(): Boolean = false
}
