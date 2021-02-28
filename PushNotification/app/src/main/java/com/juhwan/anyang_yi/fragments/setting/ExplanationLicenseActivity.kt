package com.juhwan.anyang_yi.fragments.setting

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juhwan.anyang_yi.R
import kotlinx.android.synthetic.main.activity_explanation_license.*

class ExplanationLicenseActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_explanation_license)

        val copyright = intent.getStringExtra("copyright")
        txt_license_copyright.text = copyright

        val type = intent.getStringExtra("type")
        if(type == "mit"){
            txt_license_content.text = getString(R.string.mit_license)
        } else if(type == "apache"){
            txt_license_content.text = getString(R.string.apache_license)
        } else if(type == "lottie"){
            txt_license_content.text = "Love Explosion Animation by Chris Gannon\nThank you for sharing this animation file. Below is the link\nhttps://lottiefiles.com/439-love-explosion"
        }

        imageView_back.setOnClickListener {
            finish()
        }
    }
}
