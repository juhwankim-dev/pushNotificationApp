package com.juhwan.anyang_yi.ui.notice.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ActivityAllMainNoticeBinding

class AllMainNoticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllMainNoticeBinding
    private val model: MainNoticeViewModel by viewModels()
    private lateinit var allMainNoticeAdapter: AllMainNoticeAdapter
    private var page = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllMainNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.ivBack.setOnClickListener {
            finish()
        }

        model.getAll().observe(this, Observer {
            allMainNoticeAdapter.setList(it.resultList)
            allMainNoticeAdapter.notifyItemRangeInserted((page - 1) * 15, 15)
        })

        binding.rvAllMainNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                if (!binding.rvAllMainNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount
                ) {
                    allMainNoticeAdapter.deleteLoading()
                    model.loadMainNotice(++page)
                }

            }
        })
    }

    private fun initRecyclerView() {
        binding.rvAllMainNotice.layoutManager = LinearLayoutManager(this)
        allMainNoticeAdapter = AllMainNoticeAdapter()
        binding.rvAllMainNotice.adapter = allMainNoticeAdapter
    }
}