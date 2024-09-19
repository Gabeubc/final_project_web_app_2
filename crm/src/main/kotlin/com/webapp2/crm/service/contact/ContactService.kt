package com.webapp2.crm.service.contact

import com.webapp2.crm.dto.contact.ContactDto

interface ContactService {
    fun getContacts() : List<ContactDto>
    fun getCustomers(): List<ContactDto>
    fun getProfessional(): List<ContactDto>
    fun getOperator(): List<ContactDto>
    fun getContactById(contactId: Long): ContactDto
    fun createContact(contactDto: ContactDto): Long
    fun assignToCustomer(contactId: Long): Long
    fun assignToProfessional(contactId: Long): Long
    fun assignToOperator(contactId: Long): Long
    fun addEmail(contactId: Long, emails: List<String>): Boolean
    fun deleteEmail(contactId: Long, email: String): Boolean
}