package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Contact
import com.juhwan.anyang_yi.present.utils.Result

interface ContactRepository {
    fun getContactList(): Result<Contact>
}