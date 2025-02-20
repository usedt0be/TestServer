package com.example.domain

import com.example.data.model.Chat
import com.example.data.model.Message

interface ChatRepository {
    suspend fun createChat(user1Id: String, user2Id:String): Chat

    suspend fun getMessagesForChat(chatId: String): List<Message>

    suspend fun getChatDialog(user1Id: String, user2Id: String): Chat

    suspend fun getChatById(chatId: String): Chat

    suspend fun updateChatWithMessage(message: Message, chatId: String, user1Id: String, user2Id: String)
}