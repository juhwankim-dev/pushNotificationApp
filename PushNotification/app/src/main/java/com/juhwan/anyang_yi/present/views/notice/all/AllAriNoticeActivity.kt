package com.juhwan.anyang_yi.present.views.notice.all

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityAllAriNoticeBinding
import com.juhwan.anyang_yi.present.config.BaseActivity

class AllAriNoticeActivity : BaseActivity<ActivityAllAriNoticeBinding>(R.layout.activity_all_ari_notice) {

    private val model: AriNoticeViewModel by viewModels()
    private lateinit var allAriNoticeAdapter: AllAriNoticeAdapter
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        model.loadAriNotice(page)

        binding.ivBack.setOnClickListener {
            finish()
        }

        model.getAll().observe(this, Observer {
            allAriNoticeAdapter.setList(it.ariNotice)
            allAriNoticeAdapter.notifyItemRangeInserted((page - 1) * 10, 10)
        })

        binding.rvAllAriNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                if (!binding.rvAllAriNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount
                ) {
                    allAriNoticeAdapter.deleteLoading()
                    model.loadAriNotice(++page)
                }

            }
        })
    }

    private fun initRecyclerView(){
        binding.rvAllAriNotice.layoutManager = LinearLayoutManager(this)
        allAriNoticeAdapter = AllAriNoticeAdapter()
        binding.rvAllAriNotice.adapter = allAriNoticeAdapter
    }
}