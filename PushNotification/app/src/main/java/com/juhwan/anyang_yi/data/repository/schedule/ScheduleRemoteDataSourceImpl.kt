package com.juhwan.anyang_yi.data.repository.schedule

import com.juhwan.anyang_yi.data.api.ScheduleApi
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class ScheduleRemoteDataSourceImpl @Inject constructor(
    private val scheduleApi: ScheduleApi
) : ScheduleRemoteDataSource {

    override fun getScheduleList(): Response<ResponseBody> {
        return scheduleApi.getScheduleList()
    }
}