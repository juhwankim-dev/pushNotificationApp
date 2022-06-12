package com.juhwan.anyang_yi.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnivApi {
    @GET("notice.do?mode=list")
    suspend fun getUnivNoticeList(
        @Query("srCategoryId") categoryId: String?,
        @Query("article.offset") offset: String
    ): Response<ResponseBody>

    @GET("notice.do?mode=list")
    suspend fun getRecentUnivNoticeList(): Response<ResponseBody>

    @GET("notice.do?mode=list")
    suspend fun getSearchResultList(
        @Query("srSearchVal") srSearchVal: String,
        @Query("article.offset") offset: String
    ): Response<ResponseBody>
}