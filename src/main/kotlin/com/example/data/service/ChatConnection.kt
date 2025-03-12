package com.example.data.service

import io.ktor.websocket.*

data class ChatConnection(
    val chatId: String,
    val userId: String,
    val session: WebSocketSession
)
