package com.gibson.spica.auth

data class AuthResult(
    val userId: String?,
    val emailVerified: Boolean,
)
