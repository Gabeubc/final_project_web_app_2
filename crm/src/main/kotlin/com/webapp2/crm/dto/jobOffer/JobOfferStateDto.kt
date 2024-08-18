package com.webapp2.crm.dto.jobOffer

import com.webapp2.crm.dto.utils.EntityBaseId

data class JobOfferStateDto(
    var value: String?= ""
): EntityBaseId<Long>()
