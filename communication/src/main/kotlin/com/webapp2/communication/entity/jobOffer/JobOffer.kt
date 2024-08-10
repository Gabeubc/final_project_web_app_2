package com.webapp2.communication.entity.jobOffer

import com.webapp2.communication.dto.jobOffer.JobOfferDto
import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.entity.contact.professional.Professional
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Entity(name = "WA2_JOB_OFFER")
data class JobOffer(
    @OneToMany(
        mappedBy = "jobOfferForCustomer"
    )
    var customer: Contact,
    @ManyToMany(
        mappedBy = "jobOffers",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var skills: MutableSet<Skill>,
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var recruiters: Contact,
    @OneToMany(
        mappedBy = "jobOffers",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var state: MutableSet<JobOfferStateDto>,
    @ManyToMany(
        mappedBy = "jobOffers",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var professional: MutableSet<Professional>,
    var profitMargin: Double,
    var description: String,
    var status: String,
    var creationTime: LocalDateTime,
    val publicationTime: LocalDateTime,
    var endTime: LocalDateTime,
    var value: Double = ChronoUnit.DAYS.between(publicationTime, endTime) * profitMargin
) : EntityBaseId<Long>(){
    fun toDto(): JobOfferDto {
        TODO()
    }
}