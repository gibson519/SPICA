package com.gibson.spica

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform