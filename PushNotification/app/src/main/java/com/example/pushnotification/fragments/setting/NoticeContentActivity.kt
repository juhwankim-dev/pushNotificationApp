package com.example.pushnotification.fragments.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pushnotification.R
import kotlinx.android.synthetic.main.activity_notice_content.*

class NoticeContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_content)

        var title = intent.getStringExtra("title")
        var date = intent.getStringExtra("date")
        var content = intent.getStringExtra("content")
        var isNew = intent.getBooleanExtra("isNew", false)

        txt_app_notice_title.text = title
        txt_app_notice_date.text = date
        txt_app_notice_content.text = content
        if(isNew) imageView_new.visibility = View.VISIBLE
    }
}
