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

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, waitingTime)
    }

    private fun userIdCheck() {
        if (authReference.currentUser != null) { // 이미 가입한 회원인 경우
            //userId = user.uid
        } else {
            authReference.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //userId = auth.currentUser!!.uid
                    } else {
                        showToastMessage("네트워크를 연결한 뒤 실행시켜주세요.")
                    }
                }
        }
    }
}