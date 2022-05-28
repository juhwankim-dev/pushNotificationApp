package com.juhwan.anyang_yi.present.views.home.notice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.data.repository.InitialRepository
import com.juhwan.anyang_yi.databinding.FragmentNoticeBinding
import com.juhwan.anyang_yi.present.config.BaseFragment
import com.juhwan.anyang_yi.present.views.home.RecentAriAdapter
import com.juhwan.anyang_yi.present.views.home.RecentNonsubjectAdapter
import com.juhwan.anyang_yi.present.views.home.RecentUnivAdapter
import com.juhwan.anyang_yi.present.views.home.notice.ari.AriActivity
import com.juhwan.anyang_yi.present.views.home.notice.nonsubject.NonsubjectActivity
import com.juhwan.anyang_yi.present.views.home.notice.univ.UnivActivity

class NoticeFragment : BaseFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {

    private lateinit var recentNonsubjectAdapter: RecentNonsubjectAdapter
    private lateinit var recentAriAdapter: RecentAriAdapter
    private lateinit var recentUnivAdapter: RecentUnivAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        InitialRepository.html.observe(viewLifecycleOwner, Observer{
            if(InitialRepository.apply.isEmpty()){
                InitialRepository.parsingApplyNotice()
            }

            binding!!.rvNonsubject.layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            recentNonsubjectAdapter = RecentNonsubjectAdapter()
            binding!!.rvNonsubject.adapter = recentNonsubjectAdapter
            recentNonsubjectAdapter.setList(InitialRepository.apply.subList(0, 10))
        })

        binding!!.tvSeeAllNonsubject.setOnClickListener {
            startActivity(Intent(context, NonsubjectActivity::class.java))
        }

        binding!!.tvSeeAllUniv.setOnClickListener {
            startActivity(Intent(context, UnivActivity::class.java))
        }

        binding!!.tvSeeAllAri.setOnClickListener {
            startActivity(Intent(context, AriActivity::class.java))
        }
    }

    private fun initRecyclerView(){
        binding!!.rvUniv.layoutManager = LinearLayoutManager(context)
        recentUnivAdapter = RecentUnivAdapter()
        binding!!.rvUniv.adapter = recentUnivAdapter
        //mainNoticeAdapter.setList(InitialRepository.mainNotice)

        binding!!.rvAri.layoutManager = LinearLayoutManager(context)
        recentAriAdapter = RecentAriAdapter()
        binding!!.rvAri.adapter = recentAriAdapter
        recentAriAdapter.setList(InitialRepository.ariNotice)
    }
}