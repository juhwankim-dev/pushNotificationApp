package com.example.pushnotification.fragments.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pushnotification.R
import kotlinx.android.synthetic.main.activity_license.*


class LicenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)

        recyclerView_license.adapter = LicenseAdapter()
        recyclerView_license.layoutManager = LinearLayoutManager(this)
    }
}
