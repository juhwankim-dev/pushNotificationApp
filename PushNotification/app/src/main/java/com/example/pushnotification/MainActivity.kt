package com.example.pushnotification

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val pref = this.getSharedPreferences("token", 0)
        val token: String = pref.getString("token", "default token")!!
        txt_token.text = token*/

        btn_sub.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("news").addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("결과: ","구독 요청 성공")
                    txt_token.text = "구독 중 입니다."
                } else {
                    Log.i("결과: ", "구독 요청 실패")
                }
            }
        }

        btn_unsub.setOnClickListener {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("news").addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("결과: ","구독 해제 요청 성공")
                    txt_token.text = "구독 하세요"
                } else {
                    Log.i("결과: ","구독 해제 요청 실패")
                }
            }
        }
    }
}
