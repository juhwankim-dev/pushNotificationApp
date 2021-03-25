package com.juhwan.anyang_yi.ui.setting

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ItemSettingBinding
import com.juhwan.anyang_yi.ui.setting.appnotice.AppNoticeActivity
import com.juhwan.anyang_yi.ui.setting.license.LicenseActivity
import com.juhwan.anyang_yi.ui.setting.profile.ProfileActivity

class SettingAdapter : RecyclerView.Adapter<SettingAdapter.SettingViewHolder>() {
    var items = listOf("앱 공지사항", "개발자 정보", "오픈소스 라이센스")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSettingBinding.inflate(layoutInflater, parent, false)
        return SettingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class SettingViewHolder(private val binding: ItemSettingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(str: String) {
            var menuName = ""
            var intent = Intent(itemView.context, AppNoticeActivity::class.java)

            when (str) {
                "앱 공지사항" -> {
                    menuName = "공지사항"
                }
                "문의하기" -> {
                    /*                 menuName = "문의하기"
                    intent = emailIntent(itemView.context)*/
                }
                "개발자 정보" -> {
                    menuName = "개발자 정보"
                    intent = Intent(itemView.context, ProfileActivity::class.java)
                }
                "오픈소스 라이센스" -> {
                    menuName = "오픈소스 라이센스"
                    intent = Intent(itemView.context, LicenseActivity::class.java)
                }
            }

            binding.tvSetting.text = menuName
            binding.layoutSetting.setOnClickListener {
                it.context.startActivity(intent)
            }
        }
    }
}
        //var address = arrayOf(context.getString(R.string.email))

/*        var email = Intent(Intent.ACTION_SEND)
        email.type = "plain/Text"
        email.putExtra(Intent.EXTRA_EMAIL, Uri.parse("mailto:")).apply {
            email.putExtra(
                Intent.EXTRA_TEXT,
                "앱 버전 (AppVersion): " + getAppVersion(context!!) +
                        "\n제조사 (Manufacturer): " + Build.MANUFACTURER +
                        "\n기기명 (Device): " + Build.MODEL +
                        "\n안드로이드 OS (Android OS): " + Build.VERSION.RELEASE.toString() +
                        "\n내용 (Content):\n"
            )
        }*/

/*        var email = Intent(Intent.ACTION_SEND, Uri.parse("mailto:")).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf("juhwan.dev@gmail.com"))
            putExtra(Intent.EXTRA_TEXT,
                "앱 버전 (AppVersion): " + getAppVersion(context!!) +
                        "\n제조사 (Manufacturer): " + Build.MANUFACTURER +
                        "\n기기명 (Device): " + Build.MODEL +
                        "\n안드로이드 OS (Android OS): " + Build.VERSION.RELEASE.toString() +
                        "\n내용 (Content):\n"
            )
        }

        email.type = "message/rfc822";
        return email
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
    }*/
