package com.example.util

import com.example.data.auth.FirebasePrincipal
import io.ktor.server.application.*
import io.ktor.server.auth.*


fun ApplicationCall.userId(): String? =
    this.principal<FirebasePrincipal>()?.token?.uid