package com.webapp2.crm.dto.message

import com.webapp2.crm.dto.contact.Contact
import com.webapp2.crm.dto.utils.EntityBaseId
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class Message(
    var sender: Contact? = null,
    var receivers: MutableSet<Contact>? = null,
    var interview: Interview,
    var body: String? = "",
    var date: LocalDateTime? = now()
): EntityBaseId<Long>()