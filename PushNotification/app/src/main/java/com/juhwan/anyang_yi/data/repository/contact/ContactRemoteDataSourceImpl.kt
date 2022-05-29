package com.juhwan.anyang_yi.data.repository.contact

import com.juhwan.anyang_yi.data.api.ContactApi
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class ContactRemoteDataSourceImpl @Inject constructor(
    private val contactApi: ContactApi
) : ContactRemoteDataSource {

    override fun getContactList(): Response<ResponseBody> {
        return contactApi.getContactList()
    }
}