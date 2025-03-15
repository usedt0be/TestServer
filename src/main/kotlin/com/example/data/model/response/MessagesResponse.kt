package com.example.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MessagesResponse(
    val senderId: String,
    val text: String,
    val time: String
)
