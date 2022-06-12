package com.juhwan.anyang_yi.data.repository.univ

import okhttp3.ResponseBody
import retrofit2.Response

interface UnivRemoteDataSource {
    suspend fun getUnivNoticeList(categoryId: String?, offset: String): Response<ResponseBody>
    suspend fun getRecentUnivNoticeList(): Response<ResponseBody>
    suspend fun getSearchResultList(keyword: String, offset: String): Response<ResponseBody>
}