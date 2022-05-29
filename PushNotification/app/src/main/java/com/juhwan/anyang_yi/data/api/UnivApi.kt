package com.juhwan.anyang_yi.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UnivApi {
    @FormUrlEncoded
    @POST("notice.do")
    fun getUnivNoticeList(@FieldMap fields: MutableMap<String, String>): Response<ResponseBody>
}