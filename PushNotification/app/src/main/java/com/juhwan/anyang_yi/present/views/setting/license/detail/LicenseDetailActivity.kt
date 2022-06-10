package com.juhwan.anyang_yi.present.views.setting.license.detail

import android.os.Bundle
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityLicenseDetailBinding
import com.juhwan.anyang_yi.present.config.BaseActivity

class LicenseDetailActivity : BaseActivity<ActivityLicenseDetailBinding>(R.layout.activity_license_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
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
    }

    private fun initEvent() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
