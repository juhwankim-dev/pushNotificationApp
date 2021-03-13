package com.juhwan.anyang_yi.ui.setting.license.explanation

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityExplanationLicenseBinding

class ExplanationLicenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExplanationLicenseBinding

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityExplanationLicenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLicenseCopyright.text = intent.getStringExtra("copyright")

        var licenseContent = " "
        when (intent.getStringExtra("type")) {
            "mit" -> {
                licenseContent = getString(R.string.mit_license)
            }
            "apache" -> {
                licenseContent = getString(R.string.apache_license)
            }
            "lottie" -> {
                licenseContent = "Love Explosion Animation by Chris Gannon\nThank you for sharing this animation file. Below is the link\nhttps://lottiefiles.com/439-love-explosion"
            }
        }
        binding.tvLicenseContent.text = licenseContent

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
