package com.example.util



fun generateChatId(ownUserId: String, userId: String): String {
    return listOf(ownUserId, userId).sorted().joinToString("_")
}