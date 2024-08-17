package com.webapp2.crm.entity.skill

import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.dto.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany

@Entity(name="WA2_SKILL")
data class Skill (
    var name: String? = "",
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOffers: MutableSet<JobOfferDto>
): EntityBaseId<Long>()