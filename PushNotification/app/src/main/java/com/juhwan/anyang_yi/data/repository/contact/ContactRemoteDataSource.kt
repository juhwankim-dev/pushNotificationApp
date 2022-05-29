package com.juhwan.anyang_yi.data.repository.contact

import okhttp3.ResponseBody
import retrofit2.Response

interface ContactRemoteDataSource {
    fun getContactList(): Response<ResponseBody>
}