package com.juhwan.anyang_yi.data.repository.univ

import okhttp3.ResponseBody
import retrofit2.Response

interface UnivRemoteDataSource {
    fun getUnivNoticeList(fields: MutableMap<String, String>): Response<ResponseBody>
}