package com.webapp2.crm.entity.jobOffer

import com.webapp2.crm.entity.message.Interview
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne

@Entity(name = "WA2_PROPOSAL")
data class Proposal (
    var ral : Double = 0.0,
    @OneToOne(
        mappedBy = "proposal",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var interview: Interview
): EntityBaseId<Long>()