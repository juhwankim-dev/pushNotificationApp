package com.juhwan.anyang_yi.present.views.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.databinding.FragmentScheduleBinding
import com.juhwan.anyang_yi.data.repository.ScheduleRepository
import xyz.sangcomz.stickytimelineview.callback.SectionCallback
import xyz.sangcomz.stickytimelineview.model.SectionInfo

class ScheduleFragment : Fragment() {

    private var binding: FragmentScheduleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(ScheduleRepository.isFinished.value == null){
            ScheduleRepository.loadSchedule()
            binding!!.lottieViewSheep.visibility = View.VISIBLE
            binding!!.lottieViewSheep.playAnimation()
        }

        ScheduleRepository.isFinished.observe(viewLifecycleOwner, Observer{
            binding!!.lottieViewSheep.visibility = View.GONE
            initRecyclerView()
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding!!.rvTimeline.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding!!.rvTimeline.addItemDecoration(getSectionCallback())
        binding!!.rvTimeline.adapter = ScheduleAdapter()
    }

    private fun getSectionCallback(): SectionCallback {
        var items = ScheduleRepository.schedule

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