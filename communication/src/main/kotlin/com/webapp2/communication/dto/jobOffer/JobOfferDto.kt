package com.webapp2.communication.dto.jobOffer

import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.entity.contact.professional.Professional
import com.webapp2.communication.entity.jobOffer.JobOffer
import com.webapp2.communication.entity.jobOffer.JobOfferStateDto
import com.webapp2.communication.entity.jobOffer.Skill
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class JobOfferDto(
    var customer: ContactDto,
    var skills: MutableSet<SkillDto>,
    var recruiters: ContactDto,
    var state: MutableSet<JobOfferStateDto>,
    var professional: MutableSet<Professional>,
    var profitMargin: Double,
    var description: String,
    var status: String,
    var creationTime: LocalDateTime,
    val publicationTime: LocalDateTime,
    var endTime: LocalDateTime,
    var value: Double = ChronoUnit.DAYS.between(publicationTime, endTime) * profitMargin
) : EntityBaseId<Long>(){
    fun toEntity(): JobOffer {
        TODO()
    }
}