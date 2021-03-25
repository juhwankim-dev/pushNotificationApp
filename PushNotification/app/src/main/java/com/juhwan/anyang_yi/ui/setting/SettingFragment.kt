package com.juhwan.anyang_yi.ui.setting

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.juhwan.anyang_yi.databinding.FragmentSettingBinding
import com.juhwan.anyang_yi.ui.setting.appnotice.AppNoticeActivity
import com.juhwan.anyang_yi.ui.setting.license.LicenseActivity
import com.juhwan.anyang_yi.ui.setting.license.LicenseAdapter
import com.juhwan.anyang_yi.ui.setting.profile.ProfileActivity

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

        //hasNewNotice()
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

/*    private fun hasNewNotice(){
        FirebaseDatabase.getInstance().reference
            .child("notices")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var map = p0.children.elementAt(p0.childrenCount.toInt()-1).value as Map<String, String> // 가장 마지막 공지사항의 값들을 가져옴
                    if( map["isNew"].toString() == "true" ){
                        binding!!.ivNew.visibility = View.VISIBLE
                    }
                }
            })
    }*/
}

