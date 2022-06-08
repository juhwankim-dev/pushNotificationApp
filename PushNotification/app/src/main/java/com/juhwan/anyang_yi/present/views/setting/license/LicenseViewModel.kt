package com.juhwan.anyang_yi.present.views.setting.license

import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.data.repository.LicenseRepository
import com.juhwan.anyang_yi.domain.model.License

class LicenseViewModel: ViewModel() {
    private val repository = LicenseRepository()

    fun requestLicense(): List<License> {
        return repository.requestLicense()
    }
}