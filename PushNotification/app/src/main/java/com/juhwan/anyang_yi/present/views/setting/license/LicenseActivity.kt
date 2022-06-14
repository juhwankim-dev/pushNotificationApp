package com.juhwan.anyang_yi.present.views.setting.license

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityLicenseBinding
import com.juhwan.anyang_yi.present.config.BaseActivity

class LicenseActivity : BaseActivity<ActivityLicenseBinding>(R.layout.activity_license) {
    private val model: LicenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        var licenseList = model.requestLicense()
        binding.rvLicense.adapter = LicenseAdapter(licenseList)
        binding.rvLicense.layoutManager = LinearLayoutManager(this)
    }

    private fun initEvent() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
