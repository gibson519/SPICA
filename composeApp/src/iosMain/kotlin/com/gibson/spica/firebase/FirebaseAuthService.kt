package com.gibson.spica.firebase

import cocoapods.FirebaseAuth.*
import cocoapods.FirebaseCore.*
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalForeignApi::class)
actual class FirebaseAuthService actual constructor() {
    init {
        if (FIRApp.defaultApp() == null) {
            FIRApp.configure()
        }
    }

    private val auth: FIRAuth get() = FIRAuth.auth()

    actual suspend fun signUp(email: String, password: String): AuthResult =
        suspendCancellableCoroutine { cont ->
            auth.createUserWithEmail(email, password) { result, error ->
                if (error != null) cont.resumeWithException(Exception(error.localizedDescription))
                else cont.resume(currentAuthResult())
            }
        }

    actual suspend fun signIn(email: String, password: String): AuthResult =
        suspendCancellableCoroutine { cont ->
            auth.signInWithEmail(email, password) { _, error ->
                if (error != null) cont.resumeWithException(Exception(error.localizedDescription))
                else cont.resume(currentAuthResult())
            }
        }

    actual suspend fun sendEmailVerification() {
        auth.currentUser?.sendEmailVerificationWithCompletion(null)
    }

    actual suspend fun reloadUser(): AuthResult {
        val user = auth.currentUser
        user?.reloadWithCompletion(null)
        return currentAuthResult()
    }

    actual suspend fun signOut() {
        auth.signOut(null)
    }

    actual fun currentUserId(): String? = auth.currentUser?.uid
    actual fun isEmailVerified(): Boolean = auth.currentUser?.emailVerified ?: false

    private fun currentAuthResult(): AuthResult {
        val user = auth.currentUser
        return AuthResult(
            userId = user?.uid,
            emailVerified = user?.emailVerified ?: false,
        )
    }
}
