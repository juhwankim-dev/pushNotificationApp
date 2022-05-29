package com.juhwan.anyang_yi.data.repository.ari

import com.juhwan.anyang_yi.data.api.AriApi
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AriRemoteDataSourceImpl @Inject constructor(
    private val ariApi: AriApi
) : AriRemoteDataSource {

    override fun getAriNoticeList(fields: MutableMap<String, String>): Response<ResponseBody> {
        return ariApi.getAriNoticeList(fields)
    }
}