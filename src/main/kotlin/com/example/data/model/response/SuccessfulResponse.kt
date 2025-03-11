package com.example.data.model.response

import kotlinx.serialization.Serializable


@Serializable
data class SuccessfulResponse<T>(
    val data: T? = null,
    val message: String
)
