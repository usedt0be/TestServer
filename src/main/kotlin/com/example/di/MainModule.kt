package com.example.di

import com.example.data.messagedatasource_deprecated.MessageDataSource
import com.example.data.messagedatasource_deprecated.MessageDataSourceImpl
import com.example.data.repository.ChatRepositoryImpl
import com.example.data.service.ChatInteractor
import com.example.domain.ChatRepository
import com.example.room_deprecated.RoomController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("messages_db")
    }

    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }

    single {
        RoomController(get())
    }

    single<ChatInteractor> {
        ChatInteractor(get())
    }

    single<ChatRepository> {
        ChatRepositoryImpl(get())
    }
}