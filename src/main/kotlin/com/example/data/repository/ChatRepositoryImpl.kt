package com.example.data.repository

import com.example.data.model.Chat
import com.example.data.model.Message
import com.example.domain.ChatRepository
import com.example.exception.AppException
import com.mongodb.client.model.Filters
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class ChatRepositoryImpl(
    private val db: CoroutineDatabase
): ChatRepository {

    private val chats = db.getCollection<Chat>()

    override suspend fun createChat(user1Id: String, user2Id: String): Chat {
        val chat = Chat(
            userIds = listOf(user1Id,user2Id)
        )
        chats.insertOne(chat)
        return chat
    }

    override suspend fun updateChatWithMessage(message: Message, chatId: String) {
        try {
            getChatById(chatId)
            chats.updateOne(
                filter = Chat::chatId eq chatId,
                update = push(Chat::messages, message)
            )
        } catch (e: AppException.NotFoundException) {
            throw e
        }
    }

    override suspend fun getMessagesForChat(chatId: String): List<Message> {
        val chat = chats.findOne(chatId) ?: throw AppException.NotFoundException(message = "Chat not found")
        val messages = chat.messages
        return messages
    }

    override suspend fun getChatDialog(user1Id: String, user2Id: String): Chat {
        chats.findOne(
           filter = Filters.and(
                Filters.eq(Chat::userIds.pos(0).toString(), user1Id),
                Filters.eq(Chat::userIds.pos(1).toString(), user2Id),
            )
        ) ?: chats.findOne(
            filter = Filters.and(
                Filters.eq(Chat::userIds.pos(0).toString(), user2Id),
                Filters.eq(Chat::userIds.pos(1).toString(), user1Id),
            )
        )?.let {
            return it
        }

        val newChat = createChat(user1Id = user1Id, user2Id = user2Id)
        return newChat
    }

    override suspend fun getChatById(chatId: String): Chat {
        val chat = chats.findOne(chatId) ?: throw AppException.NotFoundException(message = "Chat not found")
        return chat
    }
}