package com.juhwan.anyang_yi.present.views.home.notice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentNoticeBinding
import com.juhwan.anyang_yi.present.config.BaseFragment
import com.juhwan.anyang_yi.present.views.home.notice.ari.AriActivity
import com.juhwan.anyang_yi.present.views.home.notice.nonsubject.NonsubjectActivity
import com.juhwan.anyang_yi.present.views.home.notice.univ.UnivActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeFragment : BaseFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {
    private val viewModel: NoticeViewModel by viewModels()
    private lateinit var recentNonsubjectAdapter: RecentNonsubjectAdapter
    private lateinit var recentAriAdapter: RecentAriAdapter
    private lateinit var recentUnivAdapter: RecentUnivAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        viewModel.getRecentAriNoticeList()
        viewModel.getRecentNonsubjectNoticeList()
        viewModel.getRecentUnivNoticeList()
        initEvent()
    }

    private fun initView(){
        binding.sflNonsubject.visibility = View.VISIBLE

        binding.rvAri.layoutManager = LinearLayoutManager(context)
        recentAriAdapter = RecentAriAdapter()
        binding.rvAri.adapter = recentAriAdapter

        binding.rvNonsubject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recentNonsubjectAdapter = RecentNonsubjectAdapter()
        binding.rvNonsubject.adapter = recentNonsubjectAdapter

        binding.rvUniv.layoutManager = LinearLayoutManager(context)
        recentUnivAdapter = RecentUnivAdapter()
        binding.rvUniv.adapter = recentUnivAdapter
    }

    private fun initEvent() {
        viewModel.recentAriNoticeList.observe(viewLifecycleOwner) {
            recentAriAdapter.setList(it)
        }

        viewModel.recentNonsubjectNoticeList.observe(viewLifecycleOwner) {
            recentNonsubjectAdapter.setList(it)
            binding.sflNonsubject.visibility = View.GONE
        }

        viewModel.recentUnivNoticeList.observe(viewLifecycleOwner) {
            recentUnivAdapter.setList(it)
        }

        viewModel.problem.observe(viewLifecycleOwner) {
            showToastMessage(resources.getString(R.string.network_error))
        }

        binding.tvSeeAllNonsubject.setOnClickListener {
            startActivity(Intent(context, NonsubjectActivity::class.java))
        }

        binding.tvSeeAllUniv.setOnClickListener {
            startActivity(Intent(context, UnivActivity::class.java))
        }

        binding.tvSeeAllAri.setOnClickListener {
            startActivity(Intent(context, AriActivity::class.java))
        }
    }
}