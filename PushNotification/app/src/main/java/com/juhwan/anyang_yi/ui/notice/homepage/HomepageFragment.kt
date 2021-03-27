package com.juhwan.anyang_yi.ui.notice.homepage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.databinding.FragmentHomepageBinding
import com.juhwan.anyang_yi.repository.InitialRepository
import com.juhwan.anyang_yi.ui.notice.ApplyAdapter
import com.juhwan.anyang_yi.ui.notice.AriNoticeAdapter
import com.juhwan.anyang_yi.ui.notice.MainNoticeAdapter
import com.juhwan.anyang_yi.ui.notice.all.AllApplyActivity
import com.juhwan.anyang_yi.ui.notice.all.AllAriNoticeActivity
import com.juhwan.anyang_yi.ui.notice.all.AllMainNoticeActivity

class HomepageFragment : Fragment() {

    private var binding: FragmentHomepageBinding? = null
    private lateinit var applyAdapter: ApplyAdapter
    private lateinit var ariNoticeAdapter: AriNoticeAdapter
    private lateinit var mainNoticeAdapter: MainNoticeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomepageBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        InitialRepository.html.observe(viewLifecycleOwner, Observer{
            InitialRepository.parsingApplyNotice()

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

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView(){
        binding!!.rvMainNotice.layoutManager = LinearLayoutManager(context)
        mainNoticeAdapter = MainNoticeAdapter()
        binding!!.rvMainNotice.adapter = mainNoticeAdapter
        mainNoticeAdapter.setList(InitialRepository.mainNotice)

        binding!!.rvAriNotice.layoutManager = LinearLayoutManager(context)
        ariNoticeAdapter = AriNoticeAdapter()
        binding!!.rvAriNotice.adapter = ariNoticeAdapter
        ariNoticeAdapter.setList(InitialRepository.ariNotice.subList(0, 5))
    }
}