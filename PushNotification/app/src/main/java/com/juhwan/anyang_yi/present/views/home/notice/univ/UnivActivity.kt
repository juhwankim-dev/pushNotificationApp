package com.juhwan.anyang_yi.present.views.home.notice.univ

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityUnivBinding
import com.juhwan.anyang_yi.present.config.BaseActivity

class UnivActivity : BaseActivity<ActivityUnivBinding>(R.layout.activity_univ) {
    private val model: UnivViewModel by viewModels()
    private lateinit var univAdapter: UnivAdapter
    private var page = 1
    private var bcIdx = "0"
    private var isListEmpty = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        initTabLayout()
        model.loadMainNotice(page, bcIdx)

        binding.ivBack.setOnClickListener {
            finish()
        }

        model.getAll().observe(this, Observer {
            univAdapter.setList(it.resultList)
            isListEmpty = false
            univAdapter.notifyItemRangeInserted((page - 1) * 15, 15)
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
                    univAdapter.deleteLoading()
                    model.loadMainNotice(++page, bcIdx)
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvAllMainNotice.layoutManager = LinearLayoutManager(this)
        univAdapter = UnivAdapter()
        binding.rvAllMainNotice.adapter = univAdapter
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
                univAdapter.resetList(bcIdx)
                isListEmpty = true
                univAdapter.notifyDataSetChanged()
                model.loadMainNotice(page, bcIdx)
            }
        })
    }
}