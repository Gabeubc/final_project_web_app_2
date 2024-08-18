package com.webapp2.crm.dto.contact

import com.webapp2.crm.dto.utils.EntityBaseId

data class ProfessionalStateDto(
    var state: String ?= ""
): EntityBaseId<Long>()