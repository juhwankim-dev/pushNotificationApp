package com.juhwan.anyang_yi.present.config

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {
    companion object {
        lateinit var databaseReference: DatabaseReference
        lateinit var authReference: FirebaseAuth
        lateinit var fcmReference: FirebaseMessaging
    }

    override fun onCreate() {
        super.onCreate()

        databaseReference = FirebaseDatabase.getInstance().reference
        authReference = FirebaseAuth.getInstance()
        fcmReference = FirebaseMessaging.getInstance()
    }
}