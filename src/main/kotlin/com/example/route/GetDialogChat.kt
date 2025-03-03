package com.example.route

import com.example.data.auth.FIREBASE_AUTH
import com.example.data.model.response.SuccessfulResponse
import com.example.data.service.ChatInteractor
import com.example.exception.AppException
import com.example.util.userId
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

val logger = LoggerFactory.getLogger("GetChatDialogLogger")

fun Route.getDialogChat(
    chatInteractor: ChatInteractor
){
    authenticate(FIREBASE_AUTH) {
        get<ChatRoutes.GetDialogChat> { route -> //Creates a dialog and put it to db

            logger.info("dialog invoked")

            val ownId = call.userId() ?: throw AppException.AuthenticationException(message = "own user id was no passed")
            val userId = route.userId

            logger.info(ownId, userId)

            val chat = chatInteractor.getDialogChat(
                user1Id = ownId,
                user2Id = userId
            )

            call.respond(
                status = HttpStatusCode.OK,
                message = SuccessfulResponse(
                    data = chat,
                    message = "Chat is created"
                ),
            )
        }
    }
}





