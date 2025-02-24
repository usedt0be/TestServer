package com.example.route

import io.ktor.resources.*

object ChatRoutes {

    private const val CHATS = "/chats"
    private const val CHAT = "$CHATS/{id}"

    private const val GET_MESSAGE_FOR_CHAT = "$CHATS/{id}/messages"

    private const val GET_DIALOG_CHAT = "$CHATS/dialog/{userId}"


    const val CHAT_WEBSOCKET = "$CHATS/{chatId}/ws"

    @Resource(CHATS)
    class Chats

    @Resource(GET_MESSAGE_FOR_CHAT)
    class GetMessagesForChat(val id: String)

    @Resource(CHAT)
    class Chat(val id: Int)

    @Resource(GET_DIALOG_CHAT)
    class GetDialogChat(val userId: String)
}