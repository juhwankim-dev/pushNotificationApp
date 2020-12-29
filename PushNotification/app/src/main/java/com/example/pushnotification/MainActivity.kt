package com.example.pushnotification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_token.setOnClickListener {
            val pref = this.getSharedPreferences("token", 0)
            val token: String = pref.getString("token", "default token")!!
            txt_token.text = token
        }
    }
}
