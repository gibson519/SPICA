package com.gibson.spica.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirebaseHelper {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    // --- AUTH ---
    suspend fun signup(email: String, password: String): Result<Unit> = runCatching {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun login(email: String, password: String): Result<Unit> = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun sendEmailVerification(): Result<Unit> = runCatching {
        auth.currentUser?.sendEmailVerification()?.await()
            ?: error("No user logged in")
    }

    fun logout() {
        auth.signOut()
    }

    fun currentUserEmail(): String? = auth.currentUser?.email

    // --- FIRESTORE ---
    suspend fun saveUserProfile(uid: String, data: Map<String, Any>): Result<Unit> = runCatching {
        db.collection("users").document(uid).set(data).await()
    }
}
