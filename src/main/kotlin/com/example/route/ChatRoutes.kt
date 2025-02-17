package com.example.route

import com.example.API_VERSION

object ChatRoutes {

    private const val CHATS = "$API_VERSION/chats"

    private const val CHAT = "$CHATS/{id}"

    private const val GET_MESSAGE_FOR_CHAT = "$CHATS/{id}/messages"

    private const val GET_DIALOG_CHAT = "$CHATS/dialog/{userId}"



}