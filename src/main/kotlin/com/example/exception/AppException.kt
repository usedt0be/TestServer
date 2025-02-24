package com.example.exception


sealed class AppException(
    override val message: String,
): Exception() {

    data class NotFoundException(
        override val message: String = "Not found"
    ): AppException(message = message)

    data class UnauthorizedException(
        override val message: String = "Unauthorized"
    ): AppException(
        message = message
    )

    data class BadRequestException(
        override val message: String = "Bad Request"
    ): AppException(
        message = message
    )

    data class AuthenticationException(
        override val message: String = "Authentication exception"
    ): AppException(
        message = message
    )

}



