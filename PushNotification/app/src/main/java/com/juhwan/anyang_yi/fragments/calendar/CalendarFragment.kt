package com.juhwan.anyang_yi.fragments.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.messaging.RemoteMessage
import com.juhwan.anyang_yi.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import xyz.sangcomz.stickytimelineview.callback.SectionCallback
import xyz.sangcomz.stickytimelineview.model.SectionInfo
import java.util.*

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var schedule = ScheduleData().requestSchedule()

        //Currently only LinearLayoutManager is supported.
        recyclerview_timeline.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //Add RecyclerSectionItemDecoration.SectionCallback
        recyclerview_timeline.addItemDecoration(getSectionCallback(schedule))
        //Set Adapter
        recyclerview_timeline.adapter = ScheduleAdapter(schedule)
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