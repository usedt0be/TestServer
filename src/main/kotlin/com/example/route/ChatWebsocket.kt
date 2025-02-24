package com.example.route

import com.example.data.service.ChatInteractor
import com.example.exception.AppException
import com.example.room_deprecated.RoomController
import com.example.util.userId
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach

fun Route.chatSocket(
    chatInteractor: ChatInteractor
) {
    authenticate {
        webSocket(ChatRoutes.CHAT_WEBSOCKET) {
            val chatId = call.parameters["chatId"]
                ?: throw AppException.BadRequestException(message = "Chat id was not passed")

            val ownId = call.userId() ?: throw AppException.AuthenticationException(message = "Own user id not passed")

            chatInteractor.onConnectToChat(
                userId = ownId,
                chatId = chatId,
                session = this
            )

            try {
                incoming.consumeEach { frame ->
                    if(frame is Frame.Text) {
                        chatInteractor.sendMessage(
                            messageText = frame.readText(),
                            senderId = ownId,
                            chatId = chatId,
                        )
                    }
                }
            } catch (e:Exception) {
                //do someting
            } finally {
                chatInteractor.onDisconnectFromChat(
                    userId = ownId,
                    chatId = chatId,
                    session = this
                )
            }
        }
    }

}



//fun Route.getAllMessages(roomController: RoomController) {
//    get("/messages") {
//        call.respond(
//            HttpStatusCode.OK,
//            roomController.getAllMessages()
//        )
//    }
//}