package com.juhwan.anyang_yi.data.repository

import com.juhwan.anyang_yi.data.mapper.ContactMapper
import com.juhwan.anyang_yi.data.repository.contact.ContactRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Contact
import com.juhwan.anyang_yi.domain.repository.ContactRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactRemoteDataSource: ContactRemoteDataSource
) : ContactRepository {

    override fun getContactList(): Result<Contact> {
        return try {
            val response = contactRemoteDataSource.getContactList()

            if(response.isSuccessful && response.body() != null) {
                Result.success(ContactMapper(response.body()!!))
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }
}