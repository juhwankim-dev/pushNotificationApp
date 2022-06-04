package com.juhwan.anyang_yi.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnivApi {
    @GET("notice.do?mode=list")
    fun getUnivNoticeList(
        @Query("srCategory") categoryId: String,
        @Query("article.offset") offset: String
    ): Response<ResponseBody>
}