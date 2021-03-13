package com.juhwan.anyang_yi.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.databinding.FragmentScheduleBinding
import xyz.sangcomz.stickytimelineview.callback.SectionCallback
import xyz.sangcomz.stickytimelineview.model.SectionInfo

class ScheduleFragment : Fragment() {

    private var binding: FragmentScheduleBinding? = null
    private val model: ScheduleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var univSchedule = model.requestSchedule()
        initRecyclerView(univSchedule)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView(univSchedule: List<Schedule>) {
        binding!!.rvTimeline.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding!!.rvTimeline.addItemDecoration(getSectionCallback(univSchedule))
        binding!!.rvTimeline.adapter = ScheduleAdapter(univSchedule)
    }

    private fun getSectionCallback(items: List<Schedule>): SectionCallback {
        return object : SectionCallback {
            //In your data, implement a method to determine if this is a section.
            override fun isSection(position: Int): Boolean =
                items[position].date.substring(0, 2) != items[position - 1].date.substring(0, 2)

            //Implement a method that returns a SectionHeader.
            override fun getSectionHeader(position: Int): SectionInfo? {
                val schedule = items[position]
                return SectionInfo("2020." + schedule.date.substring(0, 2), "학사일정")
            }
        }
    }
}