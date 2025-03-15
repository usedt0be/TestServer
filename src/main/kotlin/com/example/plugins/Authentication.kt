package com.example.plugins

import com.example.data.auth.FirebasePrincipal
import com.example.data.auth.firebase
import io.ktor.server.application.*
import io.ktor.server.auth.*


fun Application.configureAuthentication(){
    install(Authentication) {
        firebase {
           validate { firebaseToken ->
               FirebasePrincipal(token = firebaseToken)
           }
        }
    }
}