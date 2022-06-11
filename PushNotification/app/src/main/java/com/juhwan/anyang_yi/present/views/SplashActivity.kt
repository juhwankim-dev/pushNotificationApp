package com.juhwan.anyang_yi.present.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivitySplashBinding
import com.juhwan.anyang_yi.present.config.ApplicationClass.Companion.authReference
import com.juhwan.anyang_yi.present.config.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val waitingTime: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userIdCheck()
    }

    private fun userIdCheck() {
        if (authReference.currentUser == null) {
            authReference.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startMainActivity()
                    } else {
                        showToastMessage("네트워크를 연결한 뒤 실행시켜주세요.")
                    }
                }
        } else {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, waitingTime)
    }
}