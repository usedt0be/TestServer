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




val token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImRjNjI2MmYzZTk3NzIzOWMwMDUzY2ViODY0Yjc3NDBmZjMxZmNkY2MiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vb3RwdmVyaWZpY2F0aW9uLWE2ZjU0IiwiYXVkIjoib3RwdmVyaWZpY2F0aW9uLWE2ZjU0IiwiYXV0aF90aW1lIjoxNzQxMDc4NDE1LCJ1c2VyX2lkIjoiQnhvT3VlSWhHbWRhMmxoY2x6amx0Z1E2MERZMiIsInN1YiI6IkJ4b091ZUloR21kYTJsaGNsempsdGdRNjBEWTIiLCJpYXQiOjE3NDEwNzg0MTYsImV4cCI6MTc0MTA4MjAxNiwicGhvbmVfbnVtYmVyIjoiKzc5MDg1OTYxMTAwIiwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJwaG9uZSI6WyIrNzkwODU5NjExMDAiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwaG9uZSJ9fQ.T0IEXWYzJaBnbqKR3pcoQAkEMt_Pdbxi60tricJz5co0AfmIbFLwTOXhcNyUiRqFiq47HRoSnMvDwFBZ1fmAKcGOICpeH0SPmKGll1qiaANLN3X9REDYRB0cQ7a_O0AG9XW_o2uMBNimboo7l4KXOIEe0W47328fMBQhjn7PvTyoTPVUmEVBRg1DVDnE8gXWFGrVD22cp7CKQTDKaz9k3GTUKjjtz9SuYMkN7y03vmGDNWAz8_mQx_1bOWNpSu9egapqq4Sqti0KSG2p2nbG_oZ41TItdjY4T6NLiZ8ABlve6AOdiQ4RR8_J-ffQNXxellCVMSdSQMMNC_ufoNRWQQ\n"



