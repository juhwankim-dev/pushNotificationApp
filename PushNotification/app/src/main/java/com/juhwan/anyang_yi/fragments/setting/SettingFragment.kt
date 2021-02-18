package com.juhwan.anyang_yi.fragments.setting

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
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_current_version.text = "현재 버전 " + getAppVersion(context!!)
        hasNewNotice()

        val pref = context!!.getSharedPreferences("pushNotificaiton", 0)
        val isPushOn = pref.getBoolean("isPushOn", true)
        switch_push_notification.isChecked = isPushOn

        switch_push_notification.setOnCheckedChangeListener { _, isChecked ->
            val pref = context!!.getSharedPreferences("pushNotificaiton", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean("isPushOn", isChecked).apply()
            editor.commit()
        }

        // 공지사항
        layout_notice.setOnClickListener {
            startActivity((Intent(activity, AppNoticeActivity::class.java)))
        }

        // 문의하기
        layout_send_email.setOnClickListener {
            sendEmail()
        }

        // 오픈소스 라이선스
        layout_license.setOnClickListener {
            startActivity((Intent(activity, LicenseActivity::class.java)))
        }

        // 프로필
        layout_profile.setOnClickListener {
            startActivity((Intent(activity, ProfileActivity::class.java)))
        }
    }

    private fun getAppVersion(context: Context): String? {
        var versionName = ""
        try {
            val pm = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = pm.versionName
        } catch (e: Exception) {
            // Exception
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
            "앱 버전 (AppVersion): " + getAppVersion(context!!) +
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
                    // 읽어오지 못했을 때
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var map = p0.children.elementAt(p0.childrenCount.toInt()-1).value as Map<String, String> // 가장 마지막 공지사항의 값들을 가져옴
                    if( map["isNew"].toString() == "true" ){
                        imageView_new.visibility = View.VISIBLE
                    }
                }
            })
    }
}
