package com.example.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


@Serializable
data class Chat(
    @BsonId
    val chatId: String = ObjectId().toString(),
    val userIds: List<String>,
    val messages: List<Message> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
)
