package com.example.route

import io.ktor.server.resources.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get


fun Route.getDialogChat(

){
    get<ChatRoutes.GetDialogChat>{
        val ownId = call

    }
}