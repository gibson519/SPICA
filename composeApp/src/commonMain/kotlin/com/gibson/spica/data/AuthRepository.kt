package com.gibson.spica.data

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

object AuthRepository {

    suspend fun signUp(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = FirebaseManager.auth
                .createUserWithEmailAndPassword(email, password)
                .await()
            result.user?.sendEmailVerification()?.await()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = FirebaseManager.auth
                .signInWithEmailAndPassword(email, password)
                .await()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendPasswordReset(email: String): Result<Unit> {
        return try {
            FirebaseManager.auth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() = FirebaseManager.auth.signOut()

    val currentUser: FirebaseUser? get() = FirebaseManager.auth.currentUser
}
