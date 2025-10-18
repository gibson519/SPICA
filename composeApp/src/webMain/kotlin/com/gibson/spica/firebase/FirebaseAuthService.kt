package com.gibson.spica.firebase

import kotlinx.coroutines.await
import kotlin.js.Promise
import kotlin.js.json

@JsModule("firebase/auth")
@JsNonModule
external object FirebaseAuthJS {
    fun createUserWithEmailAndPassword(auth: dynamic, email: String, password: String): Promise<dynamic>
    fun signInWithEmailAndPassword(auth: dynamic, email: String, password: String): Promise<dynamic>
    fun sendEmailVerification(user: dynamic): Promise<dynamic>
    fun signOut(auth: dynamic): Promise<dynamic>
}

actual class FirebaseAuthService actual constructor() {
    private val auth: dynamic get() = js("window._firebaseAuth")

    actual suspend fun signUp(email: String, password: String): AuthResult {
        FirebaseAuthJS.createUserWithEmailAndPassword(auth, email, password).await()
        return currentAuthResult()
    }

    actual suspend fun signIn(email: String, password: String): AuthResult {
        FirebaseAuthJS.signInWithEmailAndPassword(auth, email, password).await()
        return currentAuthResult()
    }

    actual suspend fun sendEmailVerification() {
        val user = auth.currentUser
        FirebaseAuthJS.sendEmailVerification(user).await()
    }

    actual suspend fun reloadUser(): AuthResult {
        auth.currentUser?.reload()?.await()
        return currentAuthResult()
    }

    actual suspend fun signOut() {
        FirebaseAuthJS.signOut(auth).await()
    }

    actual fun currentUserId(): String? = auth.currentUser?.uid as? String
    actual fun isEmailVerified(): Boolean = auth.currentUser?.emailVerified as? Boolean ?: false

    private fun currentAuthResult(): AuthResult {
        val user = auth.currentUser
        return AuthResult(
            userId = user?.uid as? String,
            emailVerified = user?.emailVerified as? Boolean ?: false,
        )
    }
}
