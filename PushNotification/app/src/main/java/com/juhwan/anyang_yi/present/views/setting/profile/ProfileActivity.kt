package com.juhwan.anyang_yi.present.views.setting.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityProfileBinding
import com.juhwan.anyang_yi.present.config.BaseActivity
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding.layout1.visibility = View.INVISIBLE
        binding.layout2.visibility = View.INVISIBLE
        binding.layout3.visibility = View.INVISIBLE

        var ani = AnimationUtils.loadAnimation(this, R.anim.fadein)
        var ani2 = AnimationUtils.loadAnimation(this, R.anim.fadein)
        var ani3 = AnimationUtils.loadAnimation(this, R.anim.fadein)

        binding.layout1.visibility = View.VISIBLE
        binding.layout1.startAnimation(ani)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.layout2.visibility = View.VISIBLE
            binding.layout2.startAnimation(ani2)
        }, 1000L)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.layout3.visibility = View.VISIBLE
            binding.layout3.startAnimation(ani3)
        }, 2000L)

        binding.ivTistoryBlog.setOnClickListener {
            var goPage = Intent(it.context, WebViewActivity::class.java)

            goPage.putExtra("url", "https://todaycode.tistory.com/")
            it.context.startActivity(goPage)
        }

        binding.ivNaverBlog.setOnClickListener {
            var goPage = Intent(it.context, WebViewActivity::class.java)

            goPage.putExtra("url", "https://blog.naver.com/mdown")
            it.context.startActivity(goPage)
        }

        binding.ivGithub.setOnClickListener {
            var goPage = Intent(it.context, WebViewActivity::class.java)

            goPage.putExtra("url", "https://github.com/juhwankim-dev/pushNotificationApp")
            it.context.startActivity(goPage)
        }

        binding.tvAppVersion.text = "현재 버전 " + getAppVersion(this)
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
