package com.juhwan.anyang_yi.present.views.schedule

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentScheduleBinding
import com.juhwan.anyang_yi.present.config.BaseFragment
import xyz.sangcomz.stickytimelineview.callback.SectionCallback
import xyz.sangcomz.stickytimelineview.model.SectionInfo

class ScheduleFragment : BaseFragment<FragmentScheduleBinding>(R.layout.fragment_schedule) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(ScheduleRepository_.isFinished.value == null){
            ScheduleRepository_.loadSchedule()
            binding!!.lottieSheep.visibility = View.VISIBLE
            binding!!.lottieSheep.playAnimation()
        }

        ScheduleRepository_.isFinished.observe(viewLifecycleOwner, Observer{
            binding!!.lottieSheep.visibility = View.GONE
            initRecyclerView()
        })
    }

    private fun initRecyclerView() {
        binding!!.rvTimeline.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding!!.rvTimeline.addItemDecoration(getSectionCallback())
        binding!!.rvTimeline.adapter = ScheduleAdapter()
    }

    private fun getSectionCallback(): SectionCallback {
        var items = ScheduleRepository_.schedule

        return object : SectionCallback {
            //In your data, implement a method to determine if this is a section.
            override fun isSection(position: Int): Boolean =
                items[position].date.substring(0, 2) != items[position - 1].date.substring(0, 2)

            //Implement a method that returns a SectionHeader.
            override fun getSectionHeader(position: Int): SectionInfo? {
                val schedule = items[position]
                return SectionInfo("2021." + schedule.date.substring(0, 2), "학사일정")
            }
        }
    }
}