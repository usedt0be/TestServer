package com.example.data.service

import com.example.data.model.Chat
import com.example.data.model.ChatSession
import com.example.data.model.Message
import com.example.domain.ChatRepository
import com.example.exception.AppException
import com.example.util.getCurrentDateTime
import com.example.util.toLong
import io.ktor.websocket.*
import org.slf4j.LoggerFactory
import java.util.*

val interactorLogger = LoggerFactory.getLogger("interactor logger")

class ChatInteractor(
    private val chatRepository: ChatRepository
) {

    private val chatConnections = Collections.synchronizedSet<ChatConnection?>(LinkedHashSet())


    private val chatTestConnection = Collections.synchronizedSet<ChatSession>(LinkedHashSet())
    fun onJoinToTestChat(
        username: String,
        sessionId: String,
        socket: WebSocketSession
    ) {
        interactorLogger.info("connection INVOKED")
        chatTestConnection += ChatSession(username = username, sessionId = sessionId, socket = socket)
    }


    fun onConnectToChat(userId: String, chatId: String, session: WebSocketSession) {
        interactorLogger.info("connection INVOKED")
        chatConnections.add(
            ChatConnection(
                userId = userId,
                chatId = chatId,
                session = session
            )
        )
    }

    fun onDisconnectFromChat(userId: String, chatId: String, session: WebSocketSession) {
        chatConnections.remove(
            ChatConnection(
                userId = userId,
                chatId = chatId,
                session = session
            )
        )
    }


    suspend fun sendMessage(messageText: String, chatId: String, senderId: String) {
        interactorLogger.info("frame sended")
        val message = Message(
            senderId = senderId,
            text = messageText,
            timestamp = getCurrentDateTime().toLong()
        )
        try {
            chatRepository.updateChatWithMessage(
                message = message,
                chatId = chatId,
            )
        } catch (e: AppException.NotFoundException) {
            throw e
        }

        chatConnections.first {
            it.chatId == chatId
        }.session.send(Frame.Text(messageText))
    }

    suspend fun getDialogChat(user1Id: String, user2Id: String): Chat {
        val chat = chatRepository.getChatDialog(user1Id, user2Id)
        return chat
    }

    suspend fun getChats(userId: String): List<Chat> = chatRepository.getChats(userId)
}