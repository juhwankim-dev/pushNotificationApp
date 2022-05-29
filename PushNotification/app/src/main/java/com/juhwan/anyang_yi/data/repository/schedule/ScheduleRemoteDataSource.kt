package com.juhwan.anyang_yi.data.repository.schedule

import okhttp3.ResponseBody
import retrofit2.Response

interface ScheduleRemoteDataSource {
    fun getScheduleList(): Response<ResponseBody>
}