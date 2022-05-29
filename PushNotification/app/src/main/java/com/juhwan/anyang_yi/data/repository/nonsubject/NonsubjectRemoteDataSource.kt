package com.juhwan.anyang_yi.data.repository.nonsubject

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.FieldMap

interface NonsubjectRemoteDataSource {
    fun getNonsubjectNoticeList(fields: MutableMap<String, String>): Response<ResponseBody>
}