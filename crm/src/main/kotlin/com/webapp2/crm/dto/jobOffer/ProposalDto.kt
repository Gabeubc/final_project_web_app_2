package com.webapp2.crm.dto.jobOffer

import com.webapp2.crm.dto.message.InterviewDto
import com.webapp2.crm.dto.utils.EntityBaseId

data class ProposalDto (
    var ral : Double = 0.0,
    var interview: InterviewDto
): EntityBaseId<Long>()