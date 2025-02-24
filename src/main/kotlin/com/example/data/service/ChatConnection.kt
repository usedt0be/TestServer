package com.example.data.service

import io.ktor.server.websocket.*
import io.ktor.websocket.*

data class ChatConnection(
    val chatId: String,
    val userId: String,
    val session: WebSocketSession
)
