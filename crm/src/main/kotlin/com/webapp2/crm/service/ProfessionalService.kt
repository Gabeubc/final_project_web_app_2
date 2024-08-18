package com.webapp2.crm.service

import com.webapp2.crm.dto.contact.ContactDto

interface ProfessionalService {
    fun getProfessionals(): List<ContactDto>
}