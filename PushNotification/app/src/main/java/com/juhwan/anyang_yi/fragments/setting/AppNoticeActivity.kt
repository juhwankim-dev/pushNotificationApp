package com.juhwan.anyang_yi.fragments.setting

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.fragment_setting.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// 앱 공지사항 리스트 보여주는 액티비티
class AppNoticeActivity : AppCompatActivity() {

    var notices = arrayListOf<AppNotice>()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_notice)

        recyclerView_app_notices.layoutManager = LinearLayoutManager(applicationContext)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val today = current.format(formatter)

        var format = SimpleDateFormat("yyyy.MM.dd")
        var firstDate = format.parse(today)

        FirebaseDatabase.getInstance().reference
            .child("notices")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    // 읽어오지 못했을 때
                }

                override fun onDataChange(p0: DataSnapshot) {
                    notices.clear()
                    p0.children.forEach {
                        var content = " "
                        var date = " "
                        var title = " "
                        var isNew = " "
                        it.children.forEach {notice ->
                            when(notice.key){
                                "content" -> content = notice.value.toString()
                                "date" -> date = notice.value.toString()
                                "title" -> title = notice.value.toString()
                                "isNew" -> isNew = notice.value.toString()
                            }
                        }

                        notices.add(0, AppNotice(content, date, title, isNew)) // 최근 게시물을 맨 위로
                    }

                    recyclerView_app_notices.adapter = AppNoticeAdapter(notices)
                }
            })

        imageView_back.setOnClickListener {
            finish()
        }
    }
}
