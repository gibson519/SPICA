package com.gibson.spica.firebase

/**
 * Represents the result of an authentication action.
 */
data class AuthResult(
    val userId: String? = null,
    val email: String? = null,
    val isVerified: Boolean = false,
    val error: String? = null
)
