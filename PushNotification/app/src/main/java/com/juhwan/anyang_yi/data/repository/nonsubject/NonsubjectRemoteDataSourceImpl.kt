package com.juhwan.anyang_yi.data.repository.nonsubject

import com.juhwan.anyang_yi.data.api.NonsubjectApi
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class NonsubjectRemoteDataSourceImpl @Inject constructor(
    private val nonsubjectApi: NonsubjectApi
) : NonsubjectRemoteDataSource {

    override suspend fun getNonsubjectNoticeList(fields: MutableMap<String, String>): Response<ResponseBody> {
        return nonsubjectApi.getNonsubjectNoticeList(fields)
    }
}