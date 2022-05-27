package com.juhwan.anyang_yi.present.views.notice.homepage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentHomepageBinding
import com.juhwan.anyang_yi.data.repository.InitialRepository
import com.juhwan.anyang_yi.present.config.BaseFragment
import com.juhwan.anyang_yi.present.views.notice.ApplyAdapter
import com.juhwan.anyang_yi.present.views.notice.AriNoticeAdapter
import com.juhwan.anyang_yi.present.views.notice.MainNoticeAdapter
import com.juhwan.anyang_yi.present.views.notice.all.AllApplyActivity
import com.juhwan.anyang_yi.present.views.notice.all.AllAriNoticeActivity
import com.juhwan.anyang_yi.present.views.notice.all.AllMainNoticeActivity

class HomepageFragment : BaseFragment<FragmentHomepageBinding>(R.layout.fragment_homepage) {

    private lateinit var applyAdapter: ApplyAdapter
    private lateinit var ariNoticeAdapter: AriNoticeAdapter
    private lateinit var mainNoticeAdapter: MainNoticeAdapter

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
            applyAdapter = ApplyAdapter()
            binding!!.rvApply.adapter = applyAdapter
            applyAdapter.setList(InitialRepository.apply.subList(0, 10))
        })

        binding!!.seeAllApply.setOnClickListener {
            startActivity(Intent(context, AllApplyActivity::class.java))
        }

        binding!!.seeAllMainNotice.setOnClickListener {
            startActivity(Intent(context, AllMainNoticeActivity::class.java))
        }

        binding!!.seeAllAriNotice.setOnClickListener {
            startActivity(Intent(context, AllAriNoticeActivity::class.java))
        }
    }

    private fun initRecyclerView(){
        binding!!.rvMainNotice.layoutManager = LinearLayoutManager(context)
        mainNoticeAdapter = MainNoticeAdapter()
        binding!!.rvMainNotice.adapter = mainNoticeAdapter
        //mainNoticeAdapter.setList(InitialRepository.mainNotice)

        binding!!.rvAriNotice.layoutManager = LinearLayoutManager(context)
        ariNoticeAdapter = AriNoticeAdapter()
        binding!!.rvAriNotice.adapter = ariNoticeAdapter
        ariNoticeAdapter.setList(InitialRepository.ariNotice)
    }
}