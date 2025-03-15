package com.example.data

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream

object FirebaseAdmin {
    private val serviceAccount: FileInputStream =
        FileInputStream("src/main/resources/otpverification-a6f54-firebase-adminsdk-x0xt8-4ba8089cea.json")

    private val options: FirebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()


    fun init(): FirebaseApp = FirebaseApp.initializeApp(options)
}