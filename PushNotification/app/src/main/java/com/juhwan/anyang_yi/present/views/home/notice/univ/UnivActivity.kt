package com.juhwan.anyang_yi.present.views.home.notice.univ

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityUnivBinding
import com.juhwan.anyang_yi.present.config.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UnivActivity : BaseActivity<ActivityUnivBinding>(R.layout.activity_univ) {
    private val viewModel: UnivViewModel by viewModels()
    private lateinit var univAdapter: UnivAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        initTabLayout()
        initEvent()
        changeCategory(ALL)
    }

    private fun initEvent() {
        binding.ivBack.setOnClickListener {
            finish()
        }
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
                val categoryId = when(tab!!.position){
                    1 -> UNIV
                    2 -> BACHELOR
                    3 -> GRADUATE_SCHOOL
                    4 -> JOB
                    5 -> BID
                    else -> ALL
                }

                changeCategory(categoryId)
            }
        })
    }

    private fun changeCategory(categoryId: String) {
        lifecycleScope.launch {
            viewModel.changeCategory(categoryId).collectLatest {
                univAdapter.submitData(lifecycle, it)
            }
        }
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