package com.example.route

import com.example.data.auth.FIREBASE_AUTH
import com.example.data.auth.FirebasePrincipal
import com.example.data.model.response.SuccessfulResponse
import com.example.data.service.ChatInteractor
import com.example.exception.AppException
import com.example.util.userId
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getDialogChat(
    chatInteractor: ChatInteractor
){

    authenticate(FIREBASE_AUTH) {
        get<ChatRoutes.GetDialogChat>{ route ->
            val ownId = call.userId()?: throw AppException.AuthenticationException(message = "own user id was no passed")
            val userId = route.userId ?: throw AppException.BadRequestException(message = "recipient id was not passed")


            val chat = chatInteractor.getDialogChat(
                user1Id = ownId,
                user2Id = userId
            )

            call.respond(
                status = HttpStatusCode.OK,
                message = SuccessfulResponse(
                    data = chat,
                    message = "Chat is created"
                )
            )
        }
    }

}





