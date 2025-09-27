package com.gibson.spica

enum class PlatformType { Android, iOS, Desktop, Web }

interface Platform {
    val name: String
    val type: PlatformType
}

expect fun getPlatform(): Platform
