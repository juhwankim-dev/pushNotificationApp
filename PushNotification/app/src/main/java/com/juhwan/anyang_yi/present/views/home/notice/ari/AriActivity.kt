package com.juhwan.anyang_yi.present.views.home.notice.ari

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityAriBinding
import com.juhwan.anyang_yi.present.config.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AriActivity : BaseActivity<ActivityAriBinding>(R.layout.activity_ari) {
    private val viewModel: AriViewModel by viewModels()
    private lateinit var ariAdapter: AriAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView(){
        binding.rvAriNotice.layoutManager = LinearLayoutManager(this)
        ariAdapter = AriAdapter()
        binding.rvAriNotice.adapter = ariAdapter
    }

    private fun initEvent() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        lifecycleScope.launch {
            viewModel.getPagingData().collectLatest {
                ariAdapter.submitData(lifecycle, it)
            }
        }
    }
}