package com.juhwan.anyang_yi.data.repository.univ

import com.juhwan.anyang_yi.data.api.UnivApi
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class UnivRemoteDataSourceImpl @Inject constructor(
    private val univApi: UnivApi
) : UnivRemoteDataSource {

    override suspend fun getUnivNoticeList(categoryId: String?, offset: String): Response<ResponseBody> {
        return univApi.getUnivNoticeList(categoryId, offset)
    }
}