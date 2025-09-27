package com.gibson.spica

class JsPlatform : Platform {
    override val name: String = "Web with Kotlin/JS"
    override val type: PlatformType = PlatformType.Web
}

actual fun getPlatform(): Platform = JsPlatform()
