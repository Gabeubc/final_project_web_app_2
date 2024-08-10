package com.webapp2.communication.service.contact

import com.webapp2.communication.entity.contact.Contact

interface ContactService {
    fun getContacts(): List<Contact>
    fun getContactById(contactId: Long): Contact
    fun saveContact(contact: Contact): Boolean
    fun updateContact(contact: Contact): Boolean
    fun deleteContact(contactId: Long): Boolean
}