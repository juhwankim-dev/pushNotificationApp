package com.juhwan.anyang_yi.domain.usecase.contact

import com.juhwan.anyang_yi.domain.model.Contact
import com.juhwan.anyang_yi.domain.repository.ContactRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {
    operator fun invoke(): Result<Contact> = contactRepository.getContactList()
}