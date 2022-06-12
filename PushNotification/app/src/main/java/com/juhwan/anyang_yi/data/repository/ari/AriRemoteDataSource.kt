package com.juhwan.anyang_yi.data.repository.ari

import okhttp3.ResponseBody
import retrofit2.Response

interface AriRemoteDataSource {
    suspend fun getAriNoticeList(fields: MutableMap<String, String>): Response<ResponseBody>
}