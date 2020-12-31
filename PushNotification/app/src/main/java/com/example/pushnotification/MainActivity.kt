package com.example.pushnotification

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val databaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val pref = this.getSharedPreferences("token", 0)
        val token: String = pref.getString("token", "default token")!!
        txt_token.text = token*/

        btn_sub.setOnClickListener {
            val keyword = editText_keyword.text.toString()

            //TODO 한글로 된 주제는 구독할 수 없다.
            if(!overlapCheck(keyword)) {
                uploadKeyword(keyword)
                Log.i("태그", "키워드가 존재하지 않아 업로드 하였습니다.")
            }

            FirebaseMessaging.getInstance().subscribeToTopic(keyword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("결과: ", "구독 요청 성공")
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

    fun overlapCheck(keyword: String): Boolean {
        var hasKeyword = false

        FirebaseDatabase.getInstance().reference
            .child("keywords")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    // 읽어오지 못했을 때
                }

                override fun onDataChange(p0: DataSnapshot) {
                    kotlin.run {
                        p0.children.forEach {
                            var map = it.value as Map<String, String>

                            if(map.containsValue(keyword)){
                                Log.i("태그", "키워드가 이미 존재합니다.")
                                hasKeyword = true
                                return@run
                            }
                        }
                    }
                }
            })

        return hasKeyword
    }

    fun uploadKeyword(keyword: String) {
        databaseReference.child("keywords").push().setValue(keyword)
    }
}
