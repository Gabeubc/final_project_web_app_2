package com.webapp2.crm.entity.contact

import com.webapp2.crm.entity.jobOffer.JobOffer
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany

@Entity(name="WA2_RECRUITER")
data class Recruiter (
    @OneToMany(
        mappedBy = "recruiter",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var contact: MutableSet<Contact>? = null,
    @OneToMany(
        mappedBy = "recruiter",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOffers: MutableSet<JobOffer>? = null
): EntityBaseId<Long>()