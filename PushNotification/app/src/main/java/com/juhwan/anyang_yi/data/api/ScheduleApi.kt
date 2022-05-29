package com.juhwan.anyang_yi.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ScheduleApi {
    @Headers("Accept-Language: ko, en-US")
    @GET("search?q=cache:https://sub.anyang.ac.kr/AYUhub_web/support/collegeInfo/coll0101.asp")
    fun getScheduleList(): Response<ResponseBody>
}