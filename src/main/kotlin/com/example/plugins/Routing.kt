package com.example.plugins

import com.example.data.service.ChatInteractor
import com.example.room_deprecated.RoomController
import com.example.route.chatSocket
import com.example.route.getChats
import com.example.route.getDialogChat
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    val chatInteractor by inject<ChatInteractor>()
    routing {
        getDialogChat(chatInteractor)
        getChats(chatInteractor)
        chatSocket(chatInteractor)

    }
}
