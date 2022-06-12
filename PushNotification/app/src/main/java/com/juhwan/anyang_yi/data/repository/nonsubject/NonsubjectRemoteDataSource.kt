package com.juhwan.anyang_yi.data.repository.nonsubject

import okhttp3.ResponseBody
import retrofit2.Response

interface NonsubjectRemoteDataSource {
    suspend fun getNonsubjectNoticeList(fields: MutableMap<String, String>): Response<ResponseBody>
}