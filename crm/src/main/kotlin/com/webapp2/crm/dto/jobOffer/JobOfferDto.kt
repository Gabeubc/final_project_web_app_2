package com.webapp2.crm.dto.jobOffer

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.dto.skill.SkillDto
import com.webapp2.crm.dto.utils.EntityBaseId
import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.temporal.ChronoUnit

data class JobOfferDto (
    var description: String? = "",
    var skills: List<SkillDto>? = null,
    var customer: ContactDto? = null,
    var professional: List<ContactDto>? = null,
    var assignedTo: ContactDto? = null,
    var operator: ContactDto? = null,
    var state: JobOfferStateDto ?= null,
    var profitMargin: Double = 0.0,
    var creationTime: LocalDateTime = now(),
    var publicationTime: LocalDateTime? = now(),
    var endTime: LocalDateTime? = now(),
    var value: Double = ChronoUnit.DAYS.between(publicationTime, endTime) * profitMargin
): EntityBaseId<Long>(), Serializable