package com.webapp2.crm.entity.jobOffer

import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.dto.jobOffer.JobOfferStateDto
import com.webapp2.crm.dto.skill.SkillDto
import com.webapp2.crm.entity.contact.Contact
import com.webapp2.crm.entity.contact.Customer
import com.webapp2.crm.entity.contact.Operator
import com.webapp2.crm.entity.contact.Professional
import com.webapp2.crm.entity.message.Interview
import com.webapp2.crm.entity.skill.Skill
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.temporal.ChronoUnit

@Entity(name = "WA2_JOB_OFFER")
data class JobOffer(
    var description: String? = "",
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var skills: MutableSet<Skill>? = mutableSetOf(),
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    var customer: Customer? = null,
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var professionals: MutableSet<Professional>? = null,
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    var operator: Operator? = null,
    @OneToMany(
        mappedBy = "jobOffer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var interviews: MutableSet<Interview>? = mutableSetOf(),
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    var assignedTo: Professional ?= null,
    @OneToMany(
        mappedBy = "jobOffer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOfferStates: MutableSet<JobOfferState>? = mutableSetOf(),
    val profitMargin: Double = 0.0,
    var creationTime: LocalDateTime = now(),
    var publicationTime: LocalDateTime? = now(),
    var endTime: LocalDateTime? = now(),
    var value: Double = ChronoUnit.DAYS.between(publicationTime, endTime) * profitMargin
) : EntityBaseId<Long>(), Serializable {
    fun toDto(): JobOfferDto {
        val dto = JobOfferDto()
        dto.id = id
        dto.description = description
        if (skills != null)
            dto.skills = skills!!.map { SkillDto(it.name) }
        if (customer != null && customer!!.contact != null)
            dto.customer = customer!!.contact!!.toDto()
        if (professionals != null)
            dto.professional = professionals!!.map { it.contact!!.toDto() }
        if (operator != null && operator!!.contact != null)
            dto.operator = operator!!.contact!!.toDto()
        if (assignedTo != null && assignedTo!!.contact != null)
            dto.assignedTo = assignedTo!!.contact!!.toDto()
        dto.profitMargin = profitMargin
        dto.creationTime = creationTime
        dto.publicationTime = publicationTime
        dto.endTime = endTime
        dto.value = value
        if (jobOfferStates != null)
            dto.state = JobOfferStateDto(jobOfferStates!!.maxBy { it1 -> it1.date }.state)
        return dto
    }

    override fun toString(): String {
        return ""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JobOffer) return false
        if (!super.equals(other)) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }

}