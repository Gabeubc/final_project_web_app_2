package com.webapp2.crm.dto.skill

import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.dto.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany

data class SkillDto (
    var name: String? = ""
): EntityBaseId<Long>()