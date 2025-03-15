package com.example.data.auth

import io.ktor.server.auth.*


const val FIREBASE_AUTH = "FIREBASE_AUTH"


fun AuthenticationConfig.firebase(
    name: String? = FIREBASE_AUTH,
    configure: FirebaseAuthConfig.() -> Unit
) {
    val provider = FirebaseAuthProvider(FirebaseAuthConfig(name).apply(configure))
    register(provider)
}