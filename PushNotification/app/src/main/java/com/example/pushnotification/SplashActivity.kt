package com.example.pushnotification

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import com.example.pushnotification.fragments.home.HtmlCrawler

class SplashActivity : AppCompatActivity(){
    val SPLASH_VIEW_TIME: Long = 2000 // 1초간 스플래시 화면을 보여줌 (ms)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({ //delay를 위한 handler
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)

        var crawler = HtmlCrawler()
        crawler.setURLAPI()
        crawler.activateBot(1)

        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}