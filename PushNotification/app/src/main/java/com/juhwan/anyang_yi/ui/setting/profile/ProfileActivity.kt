package com.juhwan.anyang_yi.ui.setting.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityMainBinding
import com.juhwan.anyang_yi.databinding.ActivityProfileBinding
import com.juhwan.anyang_yi.ui.contact.ContactDialog
import com.juhwan.anyang_yi.ui.notice.WebViewActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.ivTstoryBlog.setOnClickListener {
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
