package com.webapp2.crm.service

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.entity.contact.*
import com.webapp2.crm.repository.ContactRepository
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ContactServiceImpl(
    @Autowired
    private val contactRepository: ContactRepository
) : ContactService {
    @Transactional
    override fun getContacts(): List<ContactDto> {
        return contactRepository.findAll().map { it.toDto() }
    }
    @Transactional
    override fun getCustomers(): List<ContactDto> {
        return contactRepository.findAll()
            .asSequence()
            .map { it.customer }
            .filterNotNull()
            .map { it!!.contact!! }
            .map { it.toDto() }
            .toList()
    }
    @Transactional
    override fun getProfessional(): List<ContactDto> {
        return contactRepository.findAll()
            .asSequence()
            .map { it.professional }
            .filterNotNull()
            .map { it!!.contact!! }
            .map { it.toDto() }
            .toList()
    }
    @Transactional
    override fun getOperator(): List<ContactDto> {
        return contactRepository.findAll()
            .asSequence()
            .map { it.operator }
            .filterNotNull()
            .map { it!!.contact!! }
            .map { it.toDto() }
            .toList()
    }
    @Transactional
    override fun getContactById(contactId: Long): ContactDto {
        return contactRepository.findById(contactId).orElse(null).toDto()
    }
    @Transactional
    override fun createContact(contactDto: ContactDto): Long {
        try {
            val entity = Contact()
            entity.name = contactDto.name
            entity.surname = contactDto.surname
            val informations = contactDto
                .informations
                .map {
                    val informationEntityId = ContactInformationId()
                    informationEntityId.email = it.email
                    val informationEntity = ContactInformation(informationEntityId)
                    informationEntity.id = informationEntityId
                    informationEntity.phoneNumber = it.phoneNumber
                    informationEntity.contacts.add(entity)
                    informationEntity
                }.toMutableSet()
            entity.contactInformations!!.addAll(informations.map { it.copy() })
            contactRepository.save(entity)
            return entity.id!!
        } catch (e: Exception) {
            return EMPTY_ID
        }
    }
    @Transactional
    override fun assignToCustomer(contactId: Long): Boolean {
        try {
            val entity = contactRepository.findById(contactId).orElse(null)
            val customer = Customer()
            entity.customer = customer
            contactRepository.save(entity)
            return true
        } catch (e: Exception) {
            return false
        }
    }
    @Transactional
    override fun assignToProfessional(contactId: Long): Boolean {
        try {
            val entity = contactRepository.findById(contactId).orElse(null)
            val professional = Professional()
            entity.professional = professional
            contactRepository.save(entity)
            return true
        } catch (e: Exception) {
            return false
        }
    }
    @Transactional
    override fun assignToOperator(contactId: Long): Boolean {
        try {
            val entity = contactRepository.findById(contactId).orElse(null)
            val operator = Operator()
            entity.operator = operator
            contactRepository.save(entity)
            return true
        } catch (e: Exception) {
            return false
        }
    }
    @Transactional
    override fun addEmail(contactId: Long, emails: List<String>): Boolean {
        try {
            val entity = contactRepository.findById(contactId).orElse(null)
            emails
                .forEach {
                    val informationEntityId = ContactInformationId()
                    informationEntityId.email = it
                    val informationEntity = ContactInformation(informationEntityId)
                    informationEntity.id = informationEntityId
                    entity.contactInformations!!.add(informationEntity)
                }
            contactRepository.save(entity)
            return true
        } catch (e: Exception) {
            return false
        }
    }
    @Transactional
    override fun deleteEmail(contactId: Long, email: String): Boolean {
        try {
            val entity = contactRepository.findById(contactId).orElse(null)
            entity
                .contactInformations!!
                .removeIf {
                    it.id.email == email
                }
            contactRepository.save(entity)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}