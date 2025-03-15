package com.example.data.model.response

import kotlinx.serialization.Serializable


@Serializable
data class ChatResponse(
    val chatId: String,
    val userIds: List<String>,
    val messages: List<MessagesResponse>
)