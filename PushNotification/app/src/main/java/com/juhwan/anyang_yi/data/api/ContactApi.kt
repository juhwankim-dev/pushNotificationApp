package com.juhwan.anyang_yi.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ContactApi {
    @GET("contents.do?ciIdx=42&menuId=69")
    fun getContactList(): Response<ResponseBody>
}