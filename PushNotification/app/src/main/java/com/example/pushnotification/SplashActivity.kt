package com.example.pushnotification

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.util.Log
import com.example.pushnotification.fragments.home.HtmlCrawler

class SplashActivity : AppCompatActivity() {
    val SPLASH_VIEW_TIME: Long = 2400 // 1초간 스플래시 화면을 보여줌 (ms)

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_splash)

        var crawler = HtmlCrawler()
        crawler.requestPost(1)

        Handler().postDelayed({ //delay를 위한 handler
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)

        overridePendingTransition(R.anim.nothing, R.anim.fadeout)
    }
}