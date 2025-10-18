package com.gibson.spica.firebase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

actual class FirebaseAuthService actual constructor() {
    private val auth = FirebaseAuth.getInstance()

    actual suspend fun signUp(email: String, password: String): AuthResult {
        auth.createUserWithEmailAndPassword(email, password).await()
        return currentAuthResult()
    }

    actual suspend fun signIn(email: String, password: String): AuthResult {
        auth.signInWithEmailAndPassword(email, password).await()
        return currentAuthResult()
    }

    actual suspend fun sendEmailVerification() {
        auth.currentUser?.sendEmailVerification()?.await()
    }

    actual suspend fun reloadUser(): AuthResult {
        auth.currentUser?.reload()?.await()
        return currentAuthResult()
    }

    actual suspend fun signOut() {
        auth.signOut()
    }

    actual fun currentUserId(): String? = auth.currentUser?.uid
    actual fun isEmailVerified(): Boolean = auth.currentUser?.isEmailVerified ?: false

    private fun currentAuthResult(): AuthResult {
        val user = auth.currentUser
        return AuthResult(
            userId = user?.uid,
            emailVerified = user?.isEmailVerified ?: false,
        )
    }
}
