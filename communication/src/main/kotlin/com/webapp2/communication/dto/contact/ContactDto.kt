package com.webapp2.communication.dto.contact

import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.entity.utils.EntityBaseId

data class ContactDto(
    var name: String,
    var surname: String,
    var type: String,
    var contactInformations: MutableSet<ContactInformationDto>
): EntityBaseId<Long>(){
    fun toEntity(): Contact{
        TODO()
    }
}