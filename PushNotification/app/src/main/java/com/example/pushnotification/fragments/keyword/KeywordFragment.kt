package com.example.pushnotification.fragments.keyword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.pushnotification.R
import com.github.kimcore.inko.Inko
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.fragment_keyword.*


class KeywordFragment : Fragment(), OnItemClick {

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var map: Map<String, String> // 서버에 있는 키워드를 가져와서 저장할 변수
    lateinit private var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //DB 불러오기
        db = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .build()

        // 데이터들을 불러온다.
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

        return inflater.inflate(R.layout.fragment_keyword, container, false)
    }

    private fun subscribe() {
        val koreanKeyword = editText_keyword.text.toString()
        val inko = Inko()
        var englishkeyword = inko.ko2en(koreanKeyword)

        if (map.containsKey(englishkeyword)) {
            Log.i("태그", "키워드가 이미 존재합니다.")
            var num = map.getValue(englishkeyword).toInt() + 1 // 구독자 수 +1
            databaseReference.child("keywords").child(englishkeyword).setValue(num.toString())
        } else {
            databaseReference.child("keywords").child(englishkeyword).setValue("1")
            Log.i("태그", "키워드가 존재하지 않아 업로드 하였습니다.")
        }

        db.keywordDao().insert(Keyword(koreanKeyword))

        FirebaseMessaging.getInstance().subscribeToTopic(englishkeyword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("결과: ", "구독 요청 성공")
                } else {
                    Log.i("결과: ", "구독 요청 실패")
                }
            }

        refreshPage()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recylcerView_keywords.layoutManager = LinearLayoutManager(context)
        refreshPage()

        btn_sub.setOnClickListener {
            subscribe()
        }
    }

    override fun onClick(koreanKeyword: String) {
        val inko = Inko()
        var englishKeyword = inko.ko2en(koreanKeyword)

        FirebaseMessaging.getInstance().unsubscribeFromTopic(englishKeyword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("결과: ", "구독 해제 요청 성공")
                    var num = map.getValue(englishKeyword).toInt() - 1 // 구독자 수 -1
                    databaseReference.child("keywords").child(englishKeyword).setValue(num.toString())
                    db.keywordDao().deleteBytitle(koreanKeyword)
                    refreshPage()
                } else {
                    Log.i("결과: ", "구독 해제 요청 실패")
                }
            }
    }

    private fun refreshPage(){
        recylcerView_keywords.adapter = KeywordsAdapter(db.keywordDao().getAll(), this) // 어댑터 생성
        var keywordsAdapter = recylcerView_keywords.adapter
        keywordsAdapter!!.notifyDataSetChanged()
    }
}


/*      val pref = this.getSharedPreferences("token", 0)
      val token: String = pref.getString("token", "default token")!!
      txt_token.text = token*/