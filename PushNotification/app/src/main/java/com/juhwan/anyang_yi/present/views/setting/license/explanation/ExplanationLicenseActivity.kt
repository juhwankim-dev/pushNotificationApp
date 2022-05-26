package com.juhwan.anyang_yi.present.views.setting.license.explanation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityExplanationLicenseBinding

class ExplanationLicenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExplanationLicenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                licenseContent = getString(R.string.apache_license) + getString(R.string.lottie)
            }
        }
        binding.tvLicenseContent.text = licenseContent

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
