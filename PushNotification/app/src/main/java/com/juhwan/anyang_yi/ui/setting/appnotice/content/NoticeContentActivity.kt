package com.juhwan.anyang_yi.ui.setting.appnotice.content

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.juhwan.anyang_yi.databinding.ActivityNoticeContentBinding

class NoticeContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticeContentBinding

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityNoticeContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAppNoticeTitle.text = intent.getStringExtra("title")
        binding.tvAppNoticeDate.text = intent.getStringExtra("date")
        binding.tvAppNoticeContent.text = intent.getStringExtra("content")

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
