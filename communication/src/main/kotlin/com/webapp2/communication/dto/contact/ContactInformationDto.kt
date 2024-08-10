package com.webapp2.communication.dto.contact

import com.webapp2.communication.entity.contact.ContactInformation
import com.webapp2.communication.entity.utils.EntityBaseId

data class ContactInformationDto (
    val email: String?=null,
    val address: String?=null,
    val phoneNumber: String?=null,
    var isUpdate: Boolean,
    val contactDtos: MutableSet<ContactDto>?=null
): EntityBaseId<Long>(){
    fun toEntity(): ContactInformation{
        TODO()
    }
}