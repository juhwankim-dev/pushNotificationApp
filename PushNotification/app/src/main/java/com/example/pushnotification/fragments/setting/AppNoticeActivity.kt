package com.example.pushnotification.fragments.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pushnotification.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_notice.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// 앱 공지사항 리스트 보여주는 액티비티
class AppNoticeActivity : AppCompatActivity() {

    var notices = arrayListOf<AppNotice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        recyclerView_app_notices.layoutManager = LinearLayoutManager(applicationContext)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val today = current.format(formatter)

        var format = SimpleDateFormat("yyyy.MM.dd")
        var firstDate = format.parse(today)

        // 데이터들을 불러온다.
        FirebaseDatabase.getInstance().reference
            .child("notices")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    // 읽어오지 못했을 때
                }

                override fun onDataChange(p0: DataSnapshot) {
                    notices.clear()
                    p0.children.forEach {
                        var content = " "
                        var date = " "
                        var title = " "
                        var isNew = false
                        it.children.forEach {notice ->
                            when(notice.key){
                                "content" -> content = notice.value.toString()
                                "date" -> date = notice.value.toString()
                                "title" -> title = notice.value.toString()
                            }
                        }
                        try{
                            var secondDate = format.parse(date)
                            var calDate = firstDate.time - secondDate.time
                            var calDateDays = calDate / (24*60*60*1000)

                            if(calDateDays <= 7){ // 일주일 이내의 게시물이 존재한다면
                                isNew = true
                            }
                        }catch (e: Exception){}

                        notices.add(0, AppNotice(content, date, title, isNew)) // 최근 게시물을 맨 위로
                    }

                    recyclerView_app_notices.adapter = AppNoticeAdapter(notices)
                }
            })
    }
}
