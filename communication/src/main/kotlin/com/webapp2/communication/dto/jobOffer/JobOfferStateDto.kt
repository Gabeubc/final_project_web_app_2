package com.webapp2.communication.dto.jobOffer

import com.webapp2.communication.entity.jobOffer.JobOffer
import com.webapp2.communication.entity.utils.EntityBaseId

data class JobOfferStateDto (
    var jobOfferId: Long,
    var description: String,
    var state: String
): EntityBaseId<Long>()