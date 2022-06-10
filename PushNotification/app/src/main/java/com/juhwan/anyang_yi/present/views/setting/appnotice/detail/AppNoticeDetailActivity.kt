package com.juhwan.anyang_yi.present.views.setting.appnotice.detail

import android.os.Bundle
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityAppNoticeDetailBinding
import com.juhwan.anyang_yi.present.config.BaseActivity

class AppNoticeDetailActivity : BaseActivity<ActivityAppNoticeDetailBinding>(R.layout.activity_app_notice_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        binding.tvAppNoticeTitle.text = intent.getStringExtra("title")
        binding.tvAppNoticeDate.text = intent.getStringExtra("date")
        binding.tvAppNoticeContent.text = intent.getStringExtra("content")
    }

    private fun initEvent() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
