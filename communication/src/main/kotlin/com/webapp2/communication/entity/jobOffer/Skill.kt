package com.webapp2.communication.entity.jobOffer

import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.dto.jobOffer.JobOffer
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity(name = "WA2_SKILL")
data class Skill(
    @ManyToMany
    var jobOffers: MutableSet<JobOffer>,
    var name: String
) : EntityBaseId<Long>(){
    fun toDto(): ContactDto {
        TODO()
    }
}