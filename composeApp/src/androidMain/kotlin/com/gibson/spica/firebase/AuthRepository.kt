package com.gibson.spica.firebase

import com.gibson.spica.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

actual class AuthRepository {

    private val auth = Firebase.auth

    actual suspend fun signUp(email: String, password: String): AuthResult {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val user = auth.currentUser
            AuthResult(
                userId = user?.uid,
                email = user?.email,
                isVerified = user?.isEmailVerified == true
            )
        } catch (e: Exception) {
            AuthResult(error = e.message)
        }
    }

    actual suspend fun signIn(email: String, password: String): AuthResult {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            val user = auth.currentUser
            AuthResult(
                userId = user?.uid,
                email = user?.email,
                isVerified = user?.isEmailVerified == true
            )
        } catch (e: Exception) {
            AuthResult(error = e.message)
        }
    }

    actual suspend fun sendEmailVerification() {
        auth.currentUser?.sendEmailVerification()?.await()
    }

    actual suspend fun reloadUser(): AuthResult {
        val user = auth.currentUser
        return if (user != null) {
            user.reload().await()
            AuthResult(
                userId = user.uid,
                email = user.email,
                isVerified = user.isEmailVerified
            )
        } else {
            AuthResult(error = "No authenticated user.")
        }
    }

    actual suspend fun signOut() {
        auth.signOut()
    }
}
