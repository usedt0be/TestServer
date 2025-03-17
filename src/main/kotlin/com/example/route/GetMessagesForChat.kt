package com.example.route

import com.example.data.auth.FIREBASE_AUTH
import com.example.data.model.response.SuccessfulResponse
import com.example.data.service.ChatInteractor
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

val messagesLogger = LoggerFactory.getLogger("MESSAGES_LOGGER")
fun Route.getMessagesForChat(
    chatInteractor: ChatInteractor
) {
     authenticate(FIREBASE_AUTH) {
         messagesLogger.info("MESSAGES_HISTORY_ROUTE")
         get<ChatRoutes.GetMessagesForChat> { route ->

             val chatId = route.chatId

             val messages = chatInteractor.getMessages(chatId = chatId)
             messagesLogger.info("MESSAGES : chatId :$chatId, messages: $messages")


             call.respond(
                 status = HttpStatusCode.OK,
                 message = SuccessfulResponse(
                     data = messages,
                     message = "Successful"
                 )
             )
         }
     }
}