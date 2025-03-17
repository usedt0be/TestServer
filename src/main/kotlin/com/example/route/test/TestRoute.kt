package com.example.route.test

import com.example.data.service.ChatInteractor
import com.example.exception.AppException
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import org.slf4j.LoggerFactory

val testLogger = LoggerFactory.getLogger("Test_LOGGER")
fun Route.chatSockets(
   chatInteractor: ChatInteractor
) {
    webSocket("/chat-socket") {
        val user = call.parameters["user"]
            ?: throw AppException.BadRequestException(message = "Chat id was not passed")
        testLogger.info("$user")

        val sessionId = "51351351"

        try {
            chatInteractor.onJoinToTestChat(
                socket = this,
                username = user,
                sessionId = sessionId
            )
            incoming.consumeEach {frame ->
                if(frame is Frame.Text) {

                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            testLogger.info("DISCONNECTED!!!!!!")
        }

    }
}
