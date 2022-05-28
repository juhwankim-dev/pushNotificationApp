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

            binding!!.rvApply.layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            recentNonsubjectAdapter = RecentNonsubjectAdapter()
            binding!!.rvApply.adapter = recentNonsubjectAdapter
            recentNonsubjectAdapter.setList(InitialRepository.apply.subList(0, 10))
        })

        binding!!.seeAllApply.setOnClickListener {
            startActivity(Intent(context, NonsubjectActivity::class.java))
        }

        binding!!.seeAllMainNotice.setOnClickListener {
            startActivity(Intent(context, UnivActivity::class.java))
        }

        binding!!.seeAllAriNotice.setOnClickListener {
            startActivity(Intent(context, AriActivity::class.java))
        }
    }

    private fun initRecyclerView(){
        binding!!.rvMainNotice.layoutManager = LinearLayoutManager(context)
        recentUnivAdapter = RecentUnivAdapter()
        binding!!.rvMainNotice.adapter = recentUnivAdapter
        //mainNoticeAdapter.setList(InitialRepository.mainNotice)

        binding!!.rvAriNotice.layoutManager = LinearLayoutManager(context)
        recentAriAdapter = RecentAriAdapter()
        binding!!.rvAriNotice.adapter = recentAriAdapter
        recentAriAdapter.setList(InitialRepository.ariNotice)
    }
}