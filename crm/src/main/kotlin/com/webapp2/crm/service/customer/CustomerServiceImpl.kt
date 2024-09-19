package com.webapp2.crm.service.customer

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.entity.jobOffer.JobOffer
import com.webapp2.crm.exception.contact.ContactNotFoundException
import com.webapp2.crm.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CustomerServiceImpl(
    @Autowired
    private val customerRepository: CustomerRepository
) : CustomerService {
    @Transactional
    override fun getCustomers(): List<ContactDto> {
        return customerRepository.findAll().mapNotNull { it.contact!!.toDto(it.id) }
    }
    @Transactional
    override fun getCustomerById(customerId: Long): ContactDto {
        return customerRepository.findById(customerId).orElseThrow{ContactNotFoundException()}.contact!!.toDto()
    }
    @Transactional
    override fun getCustomersByNameOrSurname(value: String): List<ContactDto> {
        return customerRepository.findAll()
            .filterNotNull()
            .map { it.contact }
            .filter {
                it!!.name.contains(value) || it.surname.contains(value) || (it.name + " " + it.surname).contains(value)
            }.mapNotNull { it!!.toDto() }
    }

    @Transactional
    override fun getCustomerJobOffersBySkills(
        onlyPublished: Boolean, skills: List<String>, customerId: Long
    ): List<JobOfferDto> {
        return getCustomerJobOffers(onlyPublished, customerId).filter {
            it.skills!!.any { skill -> skills.contains(skill.name) }
        }.map { it.toDto() }
    }

    @Transactional
    override fun getCustomerJobOffersByProfessional(
        onlyPublished: Boolean,
        professionalName: String,
        customerId: Long
    ): List<JobOfferDto> {
        return getCustomerJobOffers(onlyPublished, customerId).filter {
            it.professionals!!.any { professional -> professional.contact!!.name.contains(professionalName) }
        }.map { it.toDto() }
    }

    @Transactional
    override fun getCustomerJobOffersByState(
        onlyPublished: Boolean, states: List<String>, customerId: Long
    ): List<JobOfferDto> {
        return getCustomerJobOffers(onlyPublished, customerId).filter {
            it.jobOfferStates!!.any { state -> states.contains(state.state) }
        }.map { it.toDto() }
    }

    @Transactional
    override fun getCustomerJobOffersByCreationTime(
        onlyPublished: Boolean, creationTime: LocalDateTime, customerId: Long
    ): List<JobOfferDto> {
        return getCustomerJobOffers(onlyPublished, customerId).filter {
            it.creationTime.isAfter(creationTime)
        }.map { it.toDto() }
    }

    @Transactional
    private fun getCustomerJobOffers(onlyPublished: Boolean, customerId: Long): List<JobOffer> {
        var jobOffers = customerRepository.findById(customerId).orElseThrow{ContactNotFoundException()}.jobOffers!!.toList()
        if (onlyPublished) jobOffers = jobOffers.filter { it.publicationTime == null }
        return jobOffers
    }
}