package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.data.model.ScheduleEntity
import com.juhwan.anyang_yi.domain.model.Schedule
import okhttp3.ResponseBody
import org.jsoup.Jsoup

object ScheduleMapper {
    operator fun invoke(scheduleEntity: ScheduleEntity): List<Schedule> {
        var list = ArrayList<Schedule>()

        scheduleEntity.items.forEach {
            list.add(
                Schedule (
                    startYear = it.startDate.substring(0, 4),
                    startMonth = it.startDate.substring(5, 7),
                    startDay = it.startDate.substring(8, 10),
                    endYear = it.endDate.substring(0, 4),
                    endMonth = it.endDate.substring(5, 7),
                    endDay = it.endDate.substring(8, 10),
                    period = it.startDate.substring(5, it.startDate.length) + "~" + it.endDate.substring(5, it.endDate.length),
                    content = it.title
                )
            )
        }

        return list
    }
}