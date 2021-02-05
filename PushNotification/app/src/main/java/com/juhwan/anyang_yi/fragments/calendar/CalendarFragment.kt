package com.juhwan.anyang_yi.fragments.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*

class CalendarFragment : Fragment() {

    var month = Calendar.MONTH - 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val min = Calendar.getInstance()
        min.add(Calendar.MONTH, (Calendar.MONTH*-1)+1)

        val max = Calendar.getInstance()
        max.add(Calendar.YEAR, 1)

        calendarView.setMinimumDate(min)
        calendarView.setMaximumDate(max)

        manageSchedule(month)
        recylcerview_schedule.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()

        calendarView.setOnPreviousPageChangeListener {
            month--
            manageSchedule(month)
        }

        calendarView.setOnForwardPageChangeListener {
            month++
            manageSchedule(month)
        }
    }

    // ScheduleData에 저장되어 있는 데이터를 가져와 리사이클러뷰로 띄워줌
    fun manageSchedule(month: Int){
        var schedule = ScheduleData().requestSchedule(month)

        val handler = android.os.Handler()
        handler.postDelayed({
            try{
                recylcerview_schedule.adapter =
                    ScheduleAdapter(
                        schedule
                    )
                var adapter = recylcerview_schedule.adapter
                adapter!!.notifyDataSetChanged()
            } catch (e: Exception){

            }
        }, 100)
    }
}