package com.example.pushnotification.fragments.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pushnotification.R
import kotlinx.android.synthetic.main.activity_explanation_license.*

class ExplanationLicenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation_license)

        val copyright = intent.getStringExtra("copyright")
        txt_license_copyright.text = copyright

        val type = intent.getStringExtra("type")
        if(type == "mit"){
            txt_license_content.text = getString(R.string.mit_license)
        } else if(type == "apache"){
            txt_license_content.text = getString(R.string.apache_license)
        }
    }
}
