package com.webapp2.crm.service

import com.webapp2.crm.dto.contact.ContactDto

interface ContactService {
    fun getContacts() : List<ContactDto>
    fun getCustomers(): List<ContactDto>
    fun getProfessional(): List<ContactDto>
    fun getOperator(): List<ContactDto>
    fun getContactById(contactId: Long): ContactDto
    fun createContact(contactDto: ContactDto): Long
    fun assignToCustomer(contactId: Long): Boolean
    fun assignToProfessional(contactId: Long): Boolean
    fun assignToOperator(contactId: Long): Boolean
    fun addEmail(contactId: Long, emails: List<String>): Boolean
    fun deleteEmail(contactId: Long, email: String): Boolean
}