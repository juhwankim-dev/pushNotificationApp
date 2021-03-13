package com.juhwan.anyang_yi.ui.setting.license

import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.repository.LicenseRepository

class LicenseViewModel: ViewModel() {
    private val repository = LicenseRepository()

    fun requestLicense(): List<License> {
        return repository.requestLicense()
    }
}