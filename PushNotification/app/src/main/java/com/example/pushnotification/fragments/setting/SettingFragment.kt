package com.example.pushnotification.fragments.setting

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationManagerCompat

import com.example.pushnotification.R
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

        layout_send_email.setOnClickListener {
            sendEmail()
        }

        layout_license.setOnClickListener {
            startActivity(Intent(Intent(activity, LicenseActivity::class.java)))
        }
    }

    fun getAppVersion(context: Context): String? {
        var versionName = ""
        try {
            val pm = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = pm.versionName
        } catch (e: Exception) {
            // Exception
        }
        return versionName
    }

    fun sendEmail(){

        var address = arrayOf(getString(R.string.email))

        var email = Intent(Intent.ACTION_SEND);
        email.setType("plain/Text");
        email.putExtra(Intent.EXTRA_EMAIL, address);
        email.putExtra(
            Intent.EXTRA_TEXT,
            "앱 버전 (AppVersion): " + getAppVersion(context!!) +
                    "\n제조사 (Manufacturer): " + Build.MANUFACTURER +
                    "\n기기명 (Device): " + Build.MODEL +
                    "\n안드로이드 OS (Android OS): " + Build.VERSION.RELEASE.toString() +
                    "\n내용 (Content):\n"
        );
        email.setType("message/rfc822");
        startActivity(email);
    }
}
