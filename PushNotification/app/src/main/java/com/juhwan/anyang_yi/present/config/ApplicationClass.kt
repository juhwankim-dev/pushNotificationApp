package com.juhwan.anyang_yi.present.config

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {
    companion object {
        lateinit var databaseReference: DatabaseReference
        lateinit var authReference: FirebaseAuth
    }

    override fun onCreate() {
        super.onCreate()

        databaseReference = FirebaseDatabase.getInstance().reference
        authReference = FirebaseAuth.getInstance()
    }
}