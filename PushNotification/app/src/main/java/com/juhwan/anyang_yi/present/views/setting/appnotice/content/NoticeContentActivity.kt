package com.juhwan.anyang_yi.present.views.setting.appnotice.content

import android.os.Bundle
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityNoticeContentBinding
import com.juhwan.anyang_yi.present.config.BaseActivity

class NoticeContentActivity : BaseActivity<ActivityNoticeContentBinding>(R.layout.activity_notice_content) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvAppNoticeTitle.text = intent.getStringExtra("title")
        binding.tvAppNoticeDate.text = intent.getStringExtra("date")
        binding.tvAppNoticeContent.text = intent.getStringExtra("content")

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
