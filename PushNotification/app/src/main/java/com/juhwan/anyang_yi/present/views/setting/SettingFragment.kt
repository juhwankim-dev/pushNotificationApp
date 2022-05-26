package com.juhwan.anyang_yi.present.views.setting

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var binding: FragmentSettingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.rvSetting.adapter = SettingAdapter()
        binding!!.rvSetting.layoutManager = LinearLayoutManager(context)
        binding!!.txtVersion.text = "현재 버전 " + getAppVersion(requireContext())
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
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
}

