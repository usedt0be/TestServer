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




fun Route.getChats(
    chatInteractor: ChatInteractor
) {
    authenticate(FIREBASE_AUTH) {
        get<ChatRoutes.Chats> { route ->
            val ownId = call.userId() ?: throw AppException.AuthenticationException(message = "Own user id not passed")

            val chats = chatInteractor.getChats(userId = ownId)

            call.respond(
                status = HttpStatusCode.OK,
                message = SuccessfulResponse(
                    data = chats,
                    message = "Successful"
                ),
            )
        }
    }
}