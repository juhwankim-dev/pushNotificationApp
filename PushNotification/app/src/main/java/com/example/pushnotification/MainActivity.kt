package com.example.pushnotification

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.github.kimcore.inko.Inko
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val databaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var map: Map<String, String> // 서버에 있는 키워드를 가져와서 저장할 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val pref = this.getSharedPreferences("token", 0)
        val token: String = pref.getString("token", "default token")!!
        txt_token.text = token*/

        /*--------------- DB 불러오기 ----------------*/
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .build()
        /*--------------- DB 불러오기 ----------------*/

        txt_my_keywords.text = db.keywordDao().getAll().toString()

        importKeywords()

        btn_sub.setOnClickListener {
            var keyword = KoreanToEnglish()

            if (map.containsValue(keyword)) {
                Log.i("태그", "키워드가 이미 존재합니다.")
            } else {
                databaseReference.child("keywords").push().setValue(keyword)
                db.keywordDao().insert(Keyword(keyword))
                txt_my_keywords.text = db.keywordDao().getAll().toString()
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
            var keyword = KoreanToEnglish()

            FirebaseMessaging.getInstance().unsubscribeFromTopic(keyword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("결과: ", "구독 해제 요청 성공")
                        db.keywordDao().deleteBytitle(keyword)
                        txt_my_keywords.text = db.keywordDao().getAll().toString()
                        txt_token.text = "구독 하세요"
                    } else {
                        Log.i("결과: ", "구독 해제 요청 실패")
                    }
                }
        }
    }

    private fun KoreanToEnglish(): String {
        var keyword = editText_keyword.text.toString()

        val inko = Inko()
        keyword = inko.ko2en(keyword)
        return keyword
    }

    private fun importKeywords() {
        FirebaseDatabase.getInstance().reference
            .child("keywords")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    // 읽어오지 못했을 때
                }

                override fun onDataChange(p0: DataSnapshot) {
                    map = p0.value as Map<String, String>
                }
            })
    }
}
