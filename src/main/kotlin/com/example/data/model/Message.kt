package com.example.data.model

import com.example.util.getCurrentDateTime
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.time.Instant

@Serializable
data class Message(
    @BsonId
    val messageId: String = ObjectId().toString(),
    val text: String,
    val senderId: String,
    val timestamp: Long,
)
