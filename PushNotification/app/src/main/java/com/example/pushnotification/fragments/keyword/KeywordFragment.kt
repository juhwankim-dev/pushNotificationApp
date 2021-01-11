package com.example.pushnotification.fragments.keyword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.pd.chocobar.ChocoBar
import kotlinx.android.synthetic.main.fragment_keyword.*
import java.lang.Exception

const val KEYWORD_LIMIT = 10

class KeywordFragment : Fragment(), OnItemClick {

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var map: Map<String, String> // 서버에 있는 키워드를 가져와서 저장할 변수
    private lateinit var db: AppDatabase

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recylcerView_keywords.adapter = KeywordsAdapter(db.keywordDao().getAll(), this)
        var keywordsAdapter = recylcerView_keywords.adapter!!
        recylcerView_keywords.layoutManager = LinearLayoutManager(context)

        refreshPage(keywordsAdapter, 0)

        btn_subscribe.setOnClickListener {
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
                    btn_subscribe.isEnabled = true
                    btn_subscribe.setTextColor(resources.getColor(R.color.mainBlue))
                } else {
                    btn_subscribe.isEnabled = false
                    btn_subscribe.setTextColor(resources.getColor(R.color.colorBlackDisabled2))
                }
            }
        })
    }

    private fun subscribe() {
        val koreanKeyword = editText_keyword.text.toString()

        if(checkExistence(koreanKeyword)){
            Snackbar.make(keywordLayout, "이미 등록된 키워드입니다.", Snackbar.LENGTH_SHORT).show();
            editText_keyword.text = null
        } else if(checkLimitOver()){
            Snackbar.make(keywordLayout, "키워드는 10개까지 등록 가능합니다.", Snackbar.LENGTH_SHORT).show();
        } else {
            editText_keyword.text = null
            val inko = Inko()
            var englishkeyword = inko.ko2en(koreanKeyword)

            FirebaseMessaging.getInstance().subscribeToTopic(englishkeyword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        normalChocoBar("잠시만 기다려주세요.")
                        if (map.containsKey(englishkeyword)) {
                            var num = map.getValue(englishkeyword).toInt() + 1 // 구독자 수 +1
                            databaseReference.child("keywords").child(englishkeyword).setValue(num.toString())
                        } else {
                            databaseReference.child("keywords").child(englishkeyword).setValue("1")
                        }

                        db.keywordDao().insert(Keyword(koreanKeyword))
                        refreshPage(recylcerView_keywords.adapter!!, 1)
                    } else {
                        redChocoBar("네트워크 상태가 불안정 합니다.")
                    }
                }
        }
    }

    fun checkExistence(koreanKeyword: String): Boolean {
        var test = db.keywordDao().getAll()
        if(test.contains(Keyword(koreanKeyword))){
            return true
        }
        return false
    }

    fun checkLimitOver(): Boolean {
        if(KEYWORD_LIMIT < db.keywordDao().getAll().size) return true
        return false
    }

    // 리사이클러뷰 안에 있는 'X'를 누른 경우
    override fun deleteKeyword(
        koreanKeyword: String
    ) {
        val inko = Inko()
        var englishKeyword = inko.ko2en(koreanKeyword)

        FirebaseMessaging.getInstance().unsubscribeFromTopic(englishKeyword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    normalChocoBar("잠시만 기다려주세요.")
                    //Snackbar.make(keywordLayout, "알림 삭제가 완료되었습니다.", Snackbar.LENGTH_SHORT).show();
                    var num = map.getValue(englishKeyword).toInt() - 1 // 구독자 수 -1
                    databaseReference.child("keywords").child(englishKeyword).setValue(num.toString())
                    db.keywordDao().deleteBytitle(koreanKeyword)
                    refreshPage(recylcerView_keywords.adapter!!, 2)
                } else {
                    redChocoBar("네트워크 상태가 불안정 합니다.")
                }
            }
    }

    private fun refreshPage(
        keywordsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        messageRequest: Int
    ) {
        val handler = android.os.Handler()

        handler.postDelayed({
            try{
                // 리사이클러뷰 새로고침
                recylcerView_keywords.adapter = KeywordsAdapter(db.keywordDao().getAll(), this)
                keywordsAdapter!!.notifyDataSetChanged()
                when (messageRequest){
                    1 -> {
                        greenChocoBar("알림을 설정하였습니다.")
                    }
                    2 -> {
                        greenChocoBar("알림을 삭제하였습니다.")
                    }
                }
            } catch (e: Exception){
                // TODO 화면을 너무 빨리 전환하면 recyclerView_keywords null 에러가 남
            }
        }, 1000)

        // 등록한 키워드 개수 새로고침
        txt_my_keywords.text = db.keywordDao().getAll().size.toString()
    }

    private fun greenChocoBar(message: String) {
        ChocoBar.builder().setView(keywordLayout)
            .setText(message)
            .setDuration(ChocoBar.LENGTH_SHORT)
            .setActionText("확인")
            .green()  // in built green ChocoBar
            .show();
    }

    private fun redChocoBar(message: String){
        ChocoBar.builder().setView(keywordLayout)
            .setText(message)
            .setDuration(ChocoBar.LENGTH_SHORT)
            .setActionText("확인")
            .red()   // in built red ChocoBar
            .show();
    }

    private fun normalChocoBar(message: String){
        ChocoBar.builder().setView(keywordLayout)
            .setText(message)
            .setDuration(ChocoBar.LENGTH_SHORT)
            .build()   // in built red ChocoBar
            .show();
    }
}


/*      val pref = this.getSharedPreferences("token", 0)
      val token: String = pref.getString("token", "default token")!!
      txt_token.text = token*/