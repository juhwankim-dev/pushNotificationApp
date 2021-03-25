package com.juhwan.anyang_yi.ui.notice.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ActivityAllAriNoticeBinding

class AllAriNoticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllAriNoticeBinding
    private val model: AriNoticeViewModel by viewModels()
    private lateinit var allAriNoticeAdapter: AllAriNoticeAdapter
    private var page = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllAriNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

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