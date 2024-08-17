package com.webapp2.crm.entity.jobOffer

import com.webapp2.crm.dto.message.InterviewDto
import com.webapp2.crm.entity.skill.Skill
import com.webapp2.crm.dto.utils.EntityBaseId
import com.webapp2.crm.entity.contact.Customer
import com.webapp2.crm.entity.contact.Operator
import com.webapp2.crm.entity.contact.Professional
import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.temporal.ChronoUnit

@Entity(name="WA2_JOB_OFFER")
data class JobOffer (
    var description: String? = "",
    @ManyToMany(
        mappedBy = "jobOffers",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var skills: MutableSet<Skill>? = null,
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var customer: Customer? = null,
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn(
        nullable = true
    )
    var professional: Professional? = null,
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var operator: Operator? = null,
    @OneToMany(
        mappedBy = "jobOffer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var interviews: MutableSet<InterviewDto> ? = null,
    @OneToMany(
        mappedBy = "jobOffer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOfferStates: MutableSet<JobOfferState> ? = null,
    val profitMargin: Double = 0.0,
    var creationTime: LocalDateTime = now(),
    var publicationTime: LocalDateTime? = null,
    var endTime: LocalDateTime? = null,
    var value: Double = ChronoUnit.DAYS.between(publicationTime, endTime) * profitMargin
): EntityBaseId<Long>(), Serializable