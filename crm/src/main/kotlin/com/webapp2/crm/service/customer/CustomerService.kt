package com.webapp2.crm.service.customer

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import java.time.LocalDateTime

interface CustomerService {
    fun getCustomers() : List<ContactDto>
    fun getCustomerJobOffersBySkills(onlyPublished: Boolean, skills: List<String>, customerId: Long): List<JobOfferDto>
    fun getCustomerJobOffersByProfessional(onlyPublished: Boolean, customerName: String, customerId: Long): List<JobOfferDto>
    fun getCustomerJobOffersByState(onlyPublished: Boolean, states: List<String>, customerId: Long): List<JobOfferDto>
    fun getCustomerJobOffersByCreationTime(onlyPublished: Boolean, creationTime: LocalDateTime, customerId: Long): List<JobOfferDto>
    fun getCustomersByNameOrSurname(value: String): List<ContactDto>
    fun getCustomerById(customerId: Long): ContactDto
}