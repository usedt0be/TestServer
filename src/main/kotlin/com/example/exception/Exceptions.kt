package com.example.exception

data class NotFoundException(
    override val message: String = "Not found"
) : Exception()

