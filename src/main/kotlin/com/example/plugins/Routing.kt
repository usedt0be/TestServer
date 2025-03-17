package com.example.plugins

import com.example.data.service.ChatInteractor
import com.example.route.chatSocket
import com.example.route.test.chatSockets
import com.example.route.getChats
import com.example.route.getDialogChat
import com.example.route.getMessagesForChat
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val chatInteractor by inject<ChatInteractor>()
    routing {
        getDialogChat(chatInteractor)
        getChats(chatInteractor)
        chatSocket(chatInteractor)

        chatSockets(chatInteractor)
        getMessagesForChat(chatInteractor)
    }
}
