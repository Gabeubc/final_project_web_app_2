package com.webapp2.communication.dto.jobOffer

import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.entity.utils.EntityBaseId

data class SkillDto(
    var jobOfferIds: MutableSet<Long>,
    var name: String
) : EntityBaseId<Long>(){
    fun toDto(): ContactDto {
        TODO()
    }
}