package com.juhwan.anyang_yi.present.views.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentSettingBinding
import com.juhwan.anyang_yi.present.config.ApplicationClass.Companion.databaseReference
import com.juhwan.anyang_yi.present.config.BaseFragment
import com.juhwan.anyang_yi.present.utils.DateUtil
import com.juhwan.anyang_yi.present.views.setting.inquiry.InquiryDialog
import com.juhwan.anyang_yi.present.views.setting.inquiry.SendClickListener

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting), SendClickListener {
    private lateinit var settingAdapter: SettingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        settingAdapter = SettingAdapter()
        binding!!.rvSetting.adapter = settingAdapter
        binding!!.rvSetting.layoutManager = LinearLayoutManager(context)
        binding!!.tvVersion.text = "현재 버전 " + getAppVersion(requireContext())
    }

    private fun initEvent() {
        settingAdapter.setInquiryClickListener(object : SettingAdapter.InquiryClickListener{
            override fun onClick() {
                InquiryDialog(requireActivity(), this@SettingFragment).createDialog()
            }
        })
    }

    private fun getAppVersion(context: Context): String? {
        var versionName = ""
        try {
            val pm = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = pm.versionName
        } catch (e: Exception) {

        }
        return versionName
    }

    override fun sendClickListener(inquiry: String) {
        databaseReference.child("inquiry").child(DateUtil.getLocalDateTime()).setValue(inquiry)
        showToastMessage("소중한 의견 감사합니다!")
    }
}

