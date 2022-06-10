package com.juhwan.anyang_yi.present.views.home.notice.univ

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityUnivBinding
import com.juhwan.anyang_yi.present.config.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnivActivity : BaseActivity<ActivityUnivBinding>(R.layout.activity_univ) {
    private val viewModel: UnivViewModel by viewModels()
    private lateinit var univAdapter: UnivAdapter
    private var isListEmpty = true
    private var categoryId = ALL
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        initTabLayout()
        viewModel.getUnivNoticeList(categoryId, offset)
        initEvent()
    }

    private fun initEvent() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        viewModel.univNoticeList.observe(this) {
            univAdapter.setList(it)
            isListEmpty = false
            univAdapter.notifyDataSetChanged()
        }

        viewModel.problem.observe(this) {
            showToastMessage(resources.getString(R.string.network_error))
        }

        binding.rvUnivNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                if (!binding.rvUnivNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount && !isListEmpty
                ) {
                    univAdapter.deleteLoading()
                    viewModel.getUnivNoticeList(categoryId, ++offset)
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvUnivNotice.layoutManager = LinearLayoutManager(this)
        univAdapter = UnivAdapter()
        binding.rvUnivNotice.adapter = univAdapter
    }

    private fun initTabLayout() {
        binding.tlUniv.tabTextColors = resources.getColorStateList(R.color.tab_icon, null)
        binding.tlUniv.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                categoryId = when(tab!!.position){
                    1 -> UNIV
                    2 -> BACHELOR
                    3 -> GRADUATE_SCHOOL
                    4 -> JOB
                    5 -> BID
                    else -> ALL
                }

                offset = 0
                univAdapter.resetList()
                isListEmpty = true
                viewModel.getUnivNoticeList(categoryId, offset)
            }
        })
    }

    // 전체(null), 대학교(17), 학사(18), 대학원(19), 취업정보(20), 입찰채용(21)
    companion object {
        const val ALL = "-1"
        const val UNIV = "17"
        const val BACHELOR = "18"
        const val GRADUATE_SCHOOL = "19"
        const val JOB = "20"
        const val BID = "21"
    }
}