package com.juhwan.anyang_yi.present.views.schedule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentScheduleBinding
import com.juhwan.anyang_yi.domain.model.Schedule
import com.juhwan.anyang_yi.present.config.BaseFragment
import com.juhwan.anyang_yi.present.utils.DateUtil
import dagger.hilt.android.AndroidEntryPoint
import xyz.sangcomz.stickytimelineview.callback.SectionCallback
import xyz.sangcomz.stickytimelineview.model.SectionInfo

@AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding>(R.layout.fragment_schedule) {
    private val viewModel: ScheduleViewModel by viewModels()
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if(ScheduleRepository_.isFinished.value == null){
//            ScheduleRepository_.loadSchedule()
//            binding!!.lottieSheep.visibility = View.VISIBLE
//            binding!!.lottieSheep.playAnimation()
//        }
//
//        ScheduleRepository_.isFinished.observe(viewLifecycleOwner, Observer{
//            binding!!.lottieSheep.visibility = View.GONE
//            initRecyclerView()
//        })
        viewModel.getScheduleList(DateUtil.getFirstDayOfThisYear(), DateUtil.getLastDayOfThisYear())
        initView()
        initEvent()
    }

    private fun initView() {
        binding!!.rvTimeline.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        scheduleAdapter = ScheduleAdapter()
        binding!!.rvTimeline.adapter = scheduleAdapter
    }

    private fun initEvent() {
        viewModel.scheduleList.observe(viewLifecycleOwner) {
            scheduleAdapter.setList(it)
            binding!!.rvTimeline.addItemDecoration(getSectionCallback(it))
        }

        viewModel.problem.observe(viewLifecycleOwner) {
            showToastMessage(resources.getString(R.string.network_error))
        }
    }

    private fun getSectionCallback(items: List<Schedule>): SectionCallback {
        return object : SectionCallback {
            //In your data, implement a method to determine if this is a section.
            override fun isSection(position: Int): Boolean =
                items[position].startMonth != items[position - 1].startMonth

            //Implement a method that returns a SectionHeader.
            override fun getSectionHeader(position: Int): SectionInfo? {
                return SectionInfo("2022." + items[position].startMonth, "학사일정")
            }
        }
    }
}