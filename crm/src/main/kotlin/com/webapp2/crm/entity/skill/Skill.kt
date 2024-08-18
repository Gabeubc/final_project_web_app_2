package com.webapp2.crm.entity.skill

import com.webapp2.crm.entity.jobOffer.JobOffer
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany

@Entity(name="WA2_SKILL")
data class Skill (
    var name: String? = "",
    @ManyToMany(
        mappedBy = "skills",
        fetch = FetchType.LAZY
    )
    var jobOffers: MutableSet<JobOffer> = mutableSetOf()
): EntityBaseId<Long>(){

}