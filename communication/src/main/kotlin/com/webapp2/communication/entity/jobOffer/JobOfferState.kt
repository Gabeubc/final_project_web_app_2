package com.webapp2.communication.entity.jobOffer

import com.webapp2.communication.dto.jobOffer.JobOffer
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany

@Entity(name = "WA2_JOB_OFFER_STATE")
data class JobOfferState (
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOffer: JobOffer,
    var description: String,
    var state: String
): EntityBaseId<Long>()