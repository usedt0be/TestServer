package com.example.data.auth

import com.google.firebase.auth.FirebaseToken
import io.ktor.server.application.*
import io.ktor.server.auth.*
import jdk.jshell.spi.ExecutionControl.NotImplementedException

class FirebaseAuthConfig(name: String?): AuthenticationProvider.Config(name) {

    var firebaseAuthFunction: AuthenticationFunction<FirebaseToken> = {
        val message = "Firebase auth validate function is no specified? user firebase {validate}"

        throw NotImplementedException(message)
    }


    fun validate(validate: suspend ApplicationCall.(FirebaseToken) -> Any?) {
        firebaseAuthFunction = validate
    }


}