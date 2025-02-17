package com.example

import com.example.di.mainModule
import io.ktor.server.application.*
import io.ktor.server.websocket.*
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

const val API_VERSION = "/api/v1"

fun Application.module() {
    install(Koin) {
        modules(mainModule)
    }
    configureSockets()
    configureSerialization()
    configureSecurity()
    configureRouting()
}


