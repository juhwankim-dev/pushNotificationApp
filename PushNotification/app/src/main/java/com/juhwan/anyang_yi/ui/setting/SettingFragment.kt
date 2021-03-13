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
import com.juhwan.anyang_yi.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.juhwan.anyang_yi.databinding.FragmentSettingBinding
import com.juhwan.anyang_yi.ui.setting.appnotice.AppNoticeActivity
import com.juhwan.anyang_yi.ui.setting.license.LicenseActivity
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

        binding!!.tvCurrentVersion.text = "현재 버전 " + getAppVersion(requireContext())
        hasNewNotice()

        val pref = requireContext().getSharedPreferences("pushNotificaiton", 0)
        val isPushOn = pref.getBoolean("isPushOn", true)
        binding!!.switchPushNotification.isChecked = isPushOn

        binding!!.switchPushNotification.setOnCheckedChangeListener { _, isChecked ->
            val pref = requireContext().getSharedPreferences("pushNotificaiton", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean("isPushOn", isChecked).apply()
            editor.commit()
        }

        // 공지사항
        binding!!.layoutNotice.setOnClickListener {
            startActivity((Intent(activity, AppNoticeActivity::class.java)))
        }

        // 문의하기
        binding!!.layoutSendEmail.setOnClickListener {
            sendEmail()
        }

        // 오픈소스 라이선스
        binding!!.layoutLicense.setOnClickListener {
            startActivity((Intent(activity, LicenseActivity::class.java)))
        }

        // 프로필
        binding!!.layoutProfile.setOnClickListener {
            startActivity((Intent(activity, ProfileActivity::class.java)))
        }
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

    private fun sendEmail(){
        var address = arrayOf(getString(R.string.email))

        var email = Intent(Intent.ACTION_SEND);
        email.type = "plain/Text";
        email.putExtra(Intent.EXTRA_EMAIL, address);
        email.putExtra(
            Intent.EXTRA_TEXT,
            "앱 버전 (AppVersion): " + getAppVersion(requireContext()) +
                    "\n제조사 (Manufacturer): " + Build.MANUFACTURER +
                    "\n기기명 (Device): " + Build.MODEL +
                    "\n안드로이드 OS (Android OS): " + Build.VERSION.RELEASE.toString() +
                    "\n내용 (Content):\n"
        );
        email.type = "message/rfc822";
        startActivity(email);
    }

    private fun hasNewNotice(){
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
    }
}
