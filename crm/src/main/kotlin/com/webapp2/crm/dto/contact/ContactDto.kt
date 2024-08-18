package com.webapp2.crm.dto.contact

import com.webapp2.crm.dto.utils.EntityBaseId

data class ContactDto(
    var name: String = "",
    var surname: String = "",
    var informations: List<ContactInformationDto> = listOf(),
    var type: String = ""
) : EntityBaseId<Long>()
