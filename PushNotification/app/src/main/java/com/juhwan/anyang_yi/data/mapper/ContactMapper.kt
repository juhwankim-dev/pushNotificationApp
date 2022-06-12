package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.data.model.ContactEntity
import com.juhwan.anyang_yi.domain.model.Contact

object ContactMapper {
    operator fun invoke(contactEntity: ContactEntity): Contact {
        var subCategory = ""
        contactEntity.apply {
            subCategory = if(department == "" && job == "") {
                category
            } else if(department == "" && job != ""){
                job
            } else if(job == "" && department != "") {
                department
            } else {
                "$department・$job"
            }
        }

        return Contact(
            category = contactEntity.category,
            subCategory = subCategory,
            department = contactEntity.department,
            job = contactEntity.job,
            location = contactEntity.location,
            tel = contactEntity.tel,
            representTel = contactEntity.tel?.substringBefore("/")?.replace("-", "")
        )
    }
}