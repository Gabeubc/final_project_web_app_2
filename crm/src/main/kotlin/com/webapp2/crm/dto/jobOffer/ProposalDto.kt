package com.webapp2.crm.entity.jobOffer

import com.webapp2.crm.dto.message.InterviewDto
import com.webapp2.crm.dto.utils.EntityBaseId
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
    var interview: InterviewDto
): EntityBaseId<Long>()