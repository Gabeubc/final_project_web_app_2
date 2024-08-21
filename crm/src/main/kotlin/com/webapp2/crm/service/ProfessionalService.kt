package com.webapp2.crm.service

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import java.time.LocalDateTime

interface ProfessionalService {
    fun getProfessionals(): List<ContactDto>
    fun getProfessionalsByNameOrSurname(value: String): List<ContactDto>
    fun getProfessionalJobOffersBySkills(onlyPublished: Boolean, skills: List<String>, professionalId: Long): List<JobOfferDto>
    fun getProfessionalJobOffersByCustomer(onlyPublished: Boolean, customerName: String, professionalId: Long): List<JobOfferDto>
    fun getProfessionalJobOffersByState(onlyPublished: Boolean, states: List<String>, professionalId: Long): List<JobOfferDto>
    fun getProfessionalJobOffersByCreationTime(onlyPublished: Boolean, creationTime: LocalDateTime, professionalId: Long): List<JobOfferDto>
    fun getProfessionalById(professionalId: Long): ContactDto
}