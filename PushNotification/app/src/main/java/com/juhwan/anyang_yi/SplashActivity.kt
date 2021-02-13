package com.juhwan.anyang_yi

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.juhwan.anyang_yi.fragments.home.CallbackPost
import com.juhwan.anyang_yi.fragments.home.HtmlCrawler
import com.juhwan.anyang_yi.fragments.home.NoticeList

class SplashActivity : AppCompatActivity(), CallbackPost {

    companion object{
        var initialPost = ArrayList<NoticeList>()
    }
    private val SPLASH_VIEW_TIME: Long = 2400 // 1초간 스플래시 화면을 보여줌 (ms)

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var crawler = HtmlCrawler(this)
        crawler.requestPost(1)

        userIdCheck()

        Handler().postDelayed({ //delay를 위한 handler
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)

        overridePendingTransition(
            R.anim.nothing,
            R.anim.fadeout
        )
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

    override fun loadPage(notices: ArrayList<NoticeList>, page: Int) {
        initialPost = notices
    }
}