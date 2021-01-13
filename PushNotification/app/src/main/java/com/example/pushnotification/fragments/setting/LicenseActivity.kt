package com.example.pushnotification.fragments.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pushnotification.R


class LicenseActivity : AppCompatActivity() {
    var items : ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)
    }
}
