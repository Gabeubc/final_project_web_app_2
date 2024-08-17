package com.webapp2.crm.dto.contact

import com.webapp2.crm.dto.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

data class ProfessionalState(
    var state: String ?= ""
): EntityBaseId<Long>()