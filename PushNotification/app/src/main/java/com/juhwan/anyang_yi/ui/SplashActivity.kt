package com.juhwan.anyang_yi.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.juhwan.anyang_yi.R

class SplashActivity : AppCompatActivity(){

    private val SPLASH_VIEW_TIME: Long = 2400 // 1초간 스플래시 화면을 보여줌 (ms)

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        userIdCheck()

        Handler(Looper.getMainLooper()).postDelayed({ //delay를 위한 handler
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)

        overridePendingTransition(R.anim.nothing, R.anim.fadeout)
    }

    private fun userIdCheck(){
        var auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if(user != null){ // 이미 가입한 회원인 경우
            //userId = user.uid
        }else{
            auth.signInAnonymously()
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        //userId = auth.currentUser!!.uid
                    }else{
                        Toast.makeText(this, "네트워크를 연결한 뒤 실행시켜주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}