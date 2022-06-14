package com.juhwan.anyang_yi.present.views.home.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivitySearchBinding
import com.juhwan.anyang_yi.present.config.BaseActivity
import com.juhwan.anyang_yi.present.utils.KeywordChecker
import com.juhwan.anyang_yi.present.views.home.notice.ari.AriAdapter
import com.juhwan.anyang_yi.present.views.home.notice.univ.UnivAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search){
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var univAdapter: UnivAdapter
    private lateinit var ariAdapter: AriAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initTabLayout()
        initEvent()
    }

    private fun initView(){
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        univAdapter = UnivAdapter()
        ariAdapter = AriAdapter()
        binding.rvSearch.adapter = univAdapter
    }

    private fun initEvent() {
        binding!!.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                try {
                    KeywordChecker.searchCheck(query ?: "")
                    lifecycleScope.launch {
                        viewModel.getSearchUnivPagingData(query!!).collectLatest {
                            univAdapter.submitData(lifecycle, it)
                        }
                    }
                    lifecycleScope.launch {
                        viewModel.getSearchAriPagingData(query!!).collectLatest {
                            ariAdapter.submitData(lifecycle, it)
                        }
                    }
                } catch (e: Exception) {
                    showToastMessage(e.message.toString())
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        univAdapter.addLoadStateListener {
            if(it.refresh is LoadState.NotLoading) {
                binding.tvNoResult.isVisible = univAdapter.itemCount < 1
            }
        }
        ariAdapter.addLoadStateListener {
            if(it.refresh is LoadState.NotLoading) {
                binding.tvNoResult.isVisible = ariAdapter.itemCount < 1
            }
        }
    }

    private fun initTabLayout() {
        binding.tlSearch.tabTextColors = resources.getColorStateList(R.color.tab_icon, null)
        binding.tlSearch.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0 -> changeCategory(UNIV)
                    else -> changeCategory(ARI)
                }
            }
        })
    }

    private fun changeCategory(category: String) {
        if(category == UNIV) {
            binding.rvSearch.adapter = univAdapter
            binding.tvNoResult.isVisible = univAdapter.itemCount < 1
        } else {
            binding.rvSearch.adapter = ariAdapter
            binding.tvNoResult.isVisible = ariAdapter.itemCount < 1
        }
    }

    companion object {
        const val UNIV = "학교"
        const val ARI = "아리포트폴리오"
    }
}