
package com.gibson.spica

enum class PlatformType { Android, iOS, Desktop, Web }

interface Platform {
    val name: String
    val type: PlatformType
}

// 👇 only declare expect, no implementation here
expect fun getPlatform(): Platform
