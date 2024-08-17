package com.webapp2.crm.entity.jobOffer

import com.webapp2.crm.dto.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity(name= "WA2_JOB_OFFER_STATE")
data class JobOfferState(
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOffer: JobOffer?= null,
    var state: String?= ""
): EntityBaseId<Long>()
