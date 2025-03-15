package com.example.data.model

import io.ktor.websocket.*
import kotlinx.serialization.Serializable


@Serializable
data class ChatSession(
    val username: String,
    val sessionId: String,
    val socket: WebSocketSession
)
