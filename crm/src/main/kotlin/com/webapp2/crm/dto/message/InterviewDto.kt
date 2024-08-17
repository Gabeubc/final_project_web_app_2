package com.webapp2.crm.dto.message

import com.webapp2.crm.dto.contact.Contact
import com.webapp2.crm.entity.jobOffer.JobOffer
import com.webapp2.crm.entity.jobOffer.Proposal
import com.webapp2.crm.dto.utils.EntityBaseId
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class Interview(
    var startTime: LocalDateTime? = now(),
    var endTime: LocalDateTime? = null,
    var link: String? = "",
    var note: String? = "",
    var type: String? = "",
    var customer: Contact? = null,
    var jobOffer: JobOffer? = null,
    var operator: Contact? = null,
    var message: Message,
    var proposal: Proposal
) : EntityBaseId<Long>()