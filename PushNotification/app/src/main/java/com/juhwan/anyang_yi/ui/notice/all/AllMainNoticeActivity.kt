package com.juhwan.anyang_yi.ui.notice.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityAllMainNoticeBinding

class AllMainNoticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllMainNoticeBinding
    private val model: MainNoticeViewModel by viewModels()
    private lateinit var allMainNoticeAdapter: AllMainNoticeAdapter
    private var page = 1
    private var bcIdx = "0"
    private var isListEmpty = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllMainNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initTabLayout()
        model.loadMainNotice(page, bcIdx)

        binding.ivBack.setOnClickListener {
            finish()
        }

        model.getAll().observe(this, Observer {
            allMainNoticeAdapter.setList(it.resultList)
            isListEmpty = false
            allMainNoticeAdapter.notifyItemRangeInserted((page - 1) * 15, 15)
        })

        binding.rvAllMainNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                if (!binding.rvAllMainNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount && !isListEmpty
                ) {
                    allMainNoticeAdapter.deleteLoading()
                    model.loadMainNotice(++page, bcIdx)
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvAllMainNotice.layoutManager = LinearLayoutManager(this)
        allMainNoticeAdapter = AllMainNoticeAdapter()
        binding.rvAllMainNotice.adapter = allMainNoticeAdapter
    }

    private fun initTabLayout() {
        binding.tabLayoutMainNoticeMenu.tabTextColors = resources.getColorStateList(R.color.tab_icon, null)
        binding.tabLayoutMainNoticeMenu.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // bcIdx
                // 전체(0), 대학교(20), 학사(80), 학사(21), 취업정보(23), 입찰채용(24)
                when(tab!!.position){
                    0 -> bcIdx = "0"
                    1 -> bcIdx = "20"
                    2 -> bcIdx = "80"
                    3 -> bcIdx = "21"
                    4 -> bcIdx = "23"
                    5 -> bcIdx = "24"
                }

                page = 1
                allMainNoticeAdapter.resetList(bcIdx)
                isListEmpty = true
                allMainNoticeAdapter.notifyDataSetChanged()
                model.loadMainNotice(page, bcIdx)
            }
        })
    }
}