package com.juhwan.anyang_yi.present.views.home.notice.ari

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityAriBinding
import com.juhwan.anyang_yi.present.config.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AriActivity : BaseActivity<ActivityAriBinding>(R.layout.activity_ari) {
    private val viewModel: AriViewModel by viewModels()
    private lateinit var ariAdapter: AriAdapter
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
        viewModel.getAriNoticeList(page)
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

        viewModel.ariNoticeList.observe(this) {
            ariAdapter.setList(it)
            ariAdapter.notifyItemRangeInserted((page - 1) * 10, 10)
        }

        viewModel.problem.observe(this) {
            showToastMessage(resources.getString(R.string.network_error))
        }

        binding.rvAriNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                if (!binding.rvAriNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount
                ) {
                    ariAdapter.deleteLoading()
                    viewModel.getAriNoticeList(++page)
                }
            }
        })
    }
}