package com.webapp2.communication.dto.jobOffer

import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.entity.jobOffer.JobOffer
import com.webapp2.communication.entity.utils.EntityBaseId
import java.time.LocalDateTime

data class JobOfferDto(
    var customer: ContactDto,
    var skillIds: MutableSet<Long>,
    var recruiterId: Long,
    var stateId: MutableSet<Long>,
    var professionalIds: MutableSet<Long>,
    var profitMargin: Double,
    var description: String,
    var status: String,
    var creationTime: LocalDateTime,
    val publicationTime: LocalDateTime,
    var endTime: LocalDateTime
) : EntityBaseId<Long>(){
    fun toEntity(): JobOffer {
        TODO()
    }
}