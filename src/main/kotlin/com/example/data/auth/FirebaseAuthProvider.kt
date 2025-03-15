package com.example.data.auth

import com.example.exception.AppException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import io.ktor.http.auth.*
import io.ktor.server.auth.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class FirebaseAuthProvider(config: FirebaseAuthConfig) : AuthenticationProvider(config) {

    val authLogger = LoggerFactory.getLogger("AUTH_LOGGER")

    private val authFunction = config.firebaseAuthFunction

    override suspend fun onAuthenticate(context: AuthenticationContext) {
        try {
            val header = context.call.request.parseAuthorizationHeader()
                ?: throw AppException.UnauthorizedException("Invalid token")

            authLogger.info("$header")

            val token = verifyFirebaseIdToken(header) ?: throw AppException.UnauthorizedException("Invalid token")
            authLogger.info("$token")
            val principal = authFunction(context.call, token) ?: throw AppException.UnauthorizedException("pizdec")

            context.principal(principal)
        } catch (e: Exception) {
            e.printStackTrace()
            authLogger.info("$e")
           throw AppException.UnauthorizedException()
        }
    }


    private suspend fun verifyFirebaseIdToken(header: HttpAuthHeader): FirebaseToken? {
        return if (header.authScheme == "Bearer" && header is HttpAuthHeader.Single) {
            withContext(Dispatchers.IO) {
                authLogger.info("Token to verify: ${header?.blob}")
                FirebaseAuth.getInstance().verifyIdToken(header.blob)
            }
        } else {
            null
        }
    }
}
