package com.webapp2.crm.dto.message

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.dto.jobOffer.ProposalDto
import com.webapp2.crm.dto.utils.EntityBaseId
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class InterviewDto(
    var startTime: LocalDateTime? = now(),
    var endTime: LocalDateTime? = null,
    var link: String? = "",
    var note: String? = "",
    var type: String? = "",
    var customer: ContactDto? = null,
    var jobOffer: JobOfferDto? = null,
    var operator: ContactDto? = null,
    var message: MessageDto,
    var proposal: ProposalDto
) : EntityBaseId<Long>()