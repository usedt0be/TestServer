package com.example

import com.example.data.FirebaseAdmin
import com.example.di.mainModule
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}



fun Application.module() {
    FirebaseAdmin.init()
    install(Koin) {
        modules(mainModule)
    }
    configureResources()
    configureAuthentication()
    configureSockets()
    configureSerialization()
    configureSecurity()
    configureRouting()
}


