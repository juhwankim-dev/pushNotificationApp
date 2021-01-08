package com.example.pushnotification.fragments.keyword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.google.android.material.snackbar.Snackbar
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

    //TODO 이미 등록한 키워드를 다시 등록했을때 아무것도 하지 말고 토스트 메시지 띄우기
    //TODO 개수제한 설정하기
    private fun subscribe() {
        val koreanKeyword = editText_keyword.text.toString()
        editText_keyword.text = null
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
                    Snackbar.make(keywordLayout, "알림 설정이 완료되었습니다.", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(keywordLayout, "알림 설정을 실패하였습니다.", Snackbar.LENGTH_SHORT).show();
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

        // 키워드 적는 칸 리스너
        editText_keyword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().trim().isNotEmpty()){
                    btn_sub.isEnabled = true
                    btn_sub.setBackgroundResource(R.drawable.border_blue_fill_round)
                } else {
                    btn_sub.isEnabled = false
                    btn_sub.setBackgroundResource(R.drawable.border_gray_fill_round)
                }
            }
        })
    }

    // 리사이클러뷰 안에 있는 'X'를 누른 경우
    override fun onClick(koreanKeyword: String) {
        val inko = Inko()
        var englishKeyword = inko.ko2en(koreanKeyword)

        FirebaseMessaging.getInstance().unsubscribeFromTopic(englishKeyword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Snackbar.make(keywordLayout, "알림 삭제가 완료되었습니다.", Snackbar.LENGTH_SHORT).show();
                    var num = map.getValue(englishKeyword).toInt() - 1 // 구독자 수 -1
                    databaseReference.child("keywords").child(englishKeyword).setValue(num.toString())
                    db.keywordDao().deleteBytitle(koreanKeyword)
                    refreshPage()
                } else {
                    Snackbar.make(keywordLayout, "알림 삭제를 실패하였습니다.", Snackbar.LENGTH_SHORT).show();
                }
            }
    }

    //TODO 전체 목록을 refresh하지 말고 변화된 부분만 새로고침하도록 방법을 강구
    private fun refreshPage(){
        // 리사이클러뷰 새로고침
        recylcerView_keywords.adapter = KeywordsAdapter(db.keywordDao().getAll(), this)
        var keywordsAdapter = recylcerView_keywords.adapter
        keywordsAdapter!!.notifyDataSetChanged()

        // 등록한 키워드 개수 새로고침
        txt_my_keywords.text = db.keywordDao().getAll().size.toString()
    }
}


/*      val pref = this.getSharedPreferences("token", 0)
      val token: String = pref.getString("token", "default token")!!
      txt_token.text = token*/