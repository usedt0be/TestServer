package com.example.data.model.response

import com.example.data.model.Chat

data class SuccessfulResponse(
    val data: Chat? = null,
    val message: String
)
