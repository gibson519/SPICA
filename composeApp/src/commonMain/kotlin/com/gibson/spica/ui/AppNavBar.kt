package com.gibson.spica.ui

/**
 * Multiplatform navigation bar entry.
 * - Android/iOS → bottom nav
 * - Desktop/Web → side nav
 */
expect fun AppNavBar(
    currentRoute: String?,
    onItemClick: (route: String) -> Unit
)
