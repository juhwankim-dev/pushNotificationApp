package com.juhwan.anyang_yi.repository

import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.data.Schedule
import com.juhwan.anyang_yi.network.ScheduleApi
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ScheduleRepository {

    var schedule = ArrayList<Schedule>()
    var isFinished = MutableLiveData<Boolean>()

    fun loadSchedule() {
        val call = ScheduleApi.createApi().getSchedule()

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        parsing(response.body()!!.string())
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }

    private fun parsing(html: String) {

        var doc = Jsoup.parse(html)
        var elementDate = doc.select(".calListTableDate")
        var elementContent = doc.select(".calListTableCon")

        for (i in 0 until elementContent.size) {
            if (elementDate[i].text().isEmpty()) { // 내용이 길어 2줄로 이어지는 경우
                var dateBackup = schedule[schedule.lastIndex].date
                var contentBackup = schedule[schedule.lastIndex].content
                schedule.removeAt(schedule.lastIndex)
                schedule.add(Schedule(dateBackup, contentBackup + elementContent[i].text()))
            } else {
                schedule.add(Schedule(elementDate[i].text(), elementContent[i].text()))
            }

        }

        isFinished.value = true
    }
}