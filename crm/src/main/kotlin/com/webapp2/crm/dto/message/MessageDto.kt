package com.webapp2.crm.dto.message

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.dto.utils.EntityBaseId
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class MessageDto(
    var sender: ContactDto? = null,
    var receivers: List<ContactDto>? = null,
    var body: String? = "",
    var date: LocalDateTime? = now(),
    var state: MessageStateDto? = null
): EntityBaseId<Long>()