package com.webapp2.crm.security

import com.webapp2.crm.dto.contact.ContactDto

interface RegistrationService {
    fun createUser(contactDto: ContactDto): String
}