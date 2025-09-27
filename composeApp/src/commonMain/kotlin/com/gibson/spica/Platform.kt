package com.gibson.spica

enum class PlatformType{ Android, iOS, Desktop, Web }

interface Platform {
    val name: String
    val type: PlatformType
}

fun getPlatform(): Platform{
    return Platform("Android", PlatformType.Android)
    return Platform("iOS", PlatformType.iOS)
    return Platform("Desktop", PlatformType.Desktop)
    return Platform("Web", PlatformType.Web)
}
