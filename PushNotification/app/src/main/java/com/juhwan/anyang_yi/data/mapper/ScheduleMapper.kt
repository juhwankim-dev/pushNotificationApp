package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.domain.model.Schedule
import okhttp3.ResponseBody
import org.jsoup.Jsoup

object ScheduleMapper {
    operator fun invoke(responseBody: ResponseBody): List<Schedule> {
        var list = ArrayList<Schedule>()

        var doc = Jsoup.parse(responseBody.toString())
        var elementDate = doc.select(".calListTableDate")
        var elementContent = doc.select(".calListTableCon")

        for (i in 0 until elementContent.size) {
            if (elementDate[i].text().isEmpty()) { // 내용이 길어 2줄로 이어지는 경우
                var dateBackup = list[list.lastIndex].date
                var contentBackup = list[list.lastIndex].content
                list.removeAt(list.lastIndex)
                list.add(Schedule(dateBackup, contentBackup + elementContent[i].text()))
            } else {
                list.add(Schedule(elementDate[i].text(), elementContent[i].text()))
            }
        }

        return list
    }
}