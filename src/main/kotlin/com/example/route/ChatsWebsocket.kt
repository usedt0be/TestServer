package com.example.route

import com.example.room_deprecated.MemberAlreadyExistsException
import com.example.room_deprecated.RoomController
import com.example.session.ChatSession
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach


//fun Route.chatsSocket(roomController: RoomController) {
//    webSocket("/chat-socket") {
//        val session = call.sessions.get<ChatSession>()
//        if(session == null) {
//            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No session"))
//            return@webSocket
//        }
//
//        try {
//            roomController.onJoin(
//                username = session.username,
//                sessionId = session.sessionId,
//                socket = this
//            )
//            incoming.consumeEach {frame ->
//                if(frame is Frame.Text) {
//                    roomController.sendMessage(
//                        senderUsername = session.username,
//                        message = frame.readText()
//                    )
//                }
//
//            }
//        } catch (e: MemberAlreadyExistsException) {
//            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "User already exists"))
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            session?.let { roomController.tryDisconnect(it.username) }
//        }
//
//        catch (e: MemberAlreadyExistsException) {
//            call.respond(HttpStatusCode.Conflict)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            roomController.tryDisconnect(session.username)
//        }
//
//    }
//}



//fun Route.getAllMessages(roomController: RoomController) {
//    get("/messages") {
//        call.respond(
//            HttpStatusCode.OK,
//            roomController.getAllMessages()
//        )
//    }
//}


