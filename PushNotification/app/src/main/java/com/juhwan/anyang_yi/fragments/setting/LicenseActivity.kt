package com.juhwan.anyang_yi.fragments.setting

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import kotlinx.android.synthetic.main.activity_license.*


class LicenseActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_license)

        recyclerView_license.adapter = LicenseAdapter()
        recyclerView_license.layoutManager = LinearLayoutManager(this)
    }
}
