package com.webapp2.communication.service.contact

import com.webapp2.communication.dto.contact.ContactInformationDto
import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.entity.contact.ContactInformation
import com.webapp2.communication.repository.contact.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContactServiceImpl(
    @Autowired
    private val contactRepository: ContactRepository
) : ContactService {
    override fun getContacts(): List<Contact> {
        return contactRepository.findAll()
    }

    override fun getContactById(contactId: Long): Contact {
        return contactRepository.findById(contactId).orElse(null)
    }

    override fun saveContact(contact: Contact): Boolean {
        try {
            contactRepository.save(contact)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun updateContact(contact: Contact): Boolean {
        try {
            val contact = contactRepository.findById(contact.id!!).orElse(null)
            contact.contactInformations
                .forEach {
                    contact.contactInformations
                        .forEach { it1 ->
                            updateContactInformation(it, it1.toDto())
                        }
                }
            contact.name = contact.name
            contact.surname = contact.surname
            contact.type = contact.type
            contactRepository.save(contact)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun deleteContact(contactId: Long): Boolean {
        try {
            contactRepository.deleteById(contactId)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun updateContactInformation(
        contactInformation: ContactInformation,
        contactInformationDto: ContactInformationDto
    ) {
        if (contactInformationDto.isUpdate && contactInformation.id!! == contactInformationDto.id) {
            if(contactInformationDto.address != null)
            contactInformation.address = contactInformationDto.address
            if(contactInformationDto.email != null)
            contactInformation.email = contactInformationDto.email
            if(contactInformationDto.phoneNumber != null)
            contactInformation.phoneNumber = contactInformationDto.phoneNumber
        }
    }
}