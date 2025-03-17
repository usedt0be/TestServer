package com.example.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Message(
    @BsonId
    val messageId: String,
    val text: String,
    val senderId: String,
    val timestamp: Long,
)
