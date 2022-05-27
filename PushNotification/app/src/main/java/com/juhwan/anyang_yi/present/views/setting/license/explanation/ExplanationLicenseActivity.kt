package com.juhwan.anyang_yi.present.views.setting.license.explanation

import android.os.Bundle
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityExplanationLicenseBinding
import com.juhwan.anyang_yi.present.config.BaseActivity

class ExplanationLicenseActivity : BaseActivity<ActivityExplanationLicenseBinding>(R.layout.activity_explanation_license) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
