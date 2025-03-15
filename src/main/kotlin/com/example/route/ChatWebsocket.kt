package com.example.route

import com.example.data.auth.FIREBASE_AUTH
import com.example.data.service.ChatInteractor
import com.example.exception.AppException
import com.example.util.userId
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import org.slf4j.LoggerFactory

val socketLogger = LoggerFactory.getLogger("Chat_SOCKET_logger")
fun Route.chatSocket(
    chatInteractor: ChatInteractor
) {
    authenticate(FIREBASE_AUTH) {
        webSocket(ChatRoutes.CHAT_WEBSOCKET) {
            val chatId = call.parameters["chatId"]
                ?: throw AppException.BadRequestException(message = "Chat id was not passed")

            val ownId = call.userId() ?: throw AppException.AuthenticationException(message = "Own user id not passed")
            DialogLogger.info("$ownId")
            chatInteractor.onConnectToChat(
                userId = ownId,
                chatId = chatId,
                session = this
            )


            try {
                incoming.consumeEach { frame ->
                    if(frame is Frame.Text) {
                        DialogLogger.info("TEXT IS : ${frame.readText()}")
                        chatInteractor.sendMessage(
                            messageText = frame.readText(),
                            senderId = ownId,
                            chatId = chatId,
                        )
                    }
                }
            } catch (e:Exception) {
                e.printStackTrace()
            } finally {
                chatInteractor.onDisconnectFromChat(
                    userId = ownId,
                    chatId = chatId,
                    session = this
                )
                DialogLogger.info("DISCONNECT INVOKED")
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