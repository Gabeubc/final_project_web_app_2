package com.webapp2.crm.service.professional

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.entity.jobOffer.JobOffer
import com.webapp2.crm.exception.contact.ContactNotFoundException
import com.webapp2.crm.repository.ProfessionalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ProfessionalServiceImpl(
    @Autowired private val professionalRepository: ProfessionalRepository
) : ProfessionalService {
    @Transactional
    override fun getProfessionals(): List<ContactDto> {
        return professionalRepository.findAll().mapNotNull { it.contact!!.toDto(it.id) }
    }
    @Transactional
    override fun getProfessionalById(professionalId: Long): ContactDto {
        return professionalRepository.findById(professionalId).orElseThrow{ ContactNotFoundException() }.contact!!.toDto()
    }

    @Transactional
    override fun getProfessionalsByNameOrSurname(value: String): List<ContactDto> {
        return professionalRepository.findAll()
            .filterNotNull()
            .map { it.contact }
            .filter {
                it!!.name.contains(value) || it.surname.contains(value) || (it.name + " " + it.surname).contains(value)
            }.mapNotNull { it!!.toDto() }
    }

    @Transactional
    override fun getProfessionalJobOffersBySkills(
        onlyPublished: Boolean, skills: List<String>, professionalId: Long
    ): List<JobOfferDto> {
        return getProfessionalJobOffers(onlyPublished, professionalId).filter {
            it.skills!!.any { skill -> skills.contains(skill.name) }
        }.map { it.toDto() }
    }

    @Transactional
    override fun getProfessionalJobOffersByCustomer(
        onlyPublished: Boolean, customerName: String, professionalId: Long
    ): List<JobOfferDto> {
        return getProfessionalJobOffers(onlyPublished, professionalId).filter {
            it.customer!!.contact!!.name.contains(customerName)
        }.map { it.toDto() }
    }

    @Transactional
    override fun getProfessionalJobOffersByState(
        onlyPublished: Boolean, states: List<String>, professionalId: Long
    ): List<JobOfferDto> {
        return getProfessionalJobOffers(onlyPublished, professionalId).filter {
            it.jobOfferStates!!.any { state -> states.contains(state.state) }
        }.map { it.toDto() }
    }

    @Transactional
    override fun getProfessionalJobOffersByCreationTime(
        onlyPublished: Boolean, creationTime: LocalDateTime, professionalId: Long
    ): List<JobOfferDto> {
        return getProfessionalJobOffers(onlyPublished, professionalId).filter {
            it.creationTime.isAfter(creationTime)
        }.map { it.toDto() }
    }

    @Transactional
    private fun getProfessionalJobOffers(onlyPublished: Boolean, professionalId: Long): List<JobOffer> {
        var jobOffers = professionalRepository.findById(professionalId).orElseThrow{ContactNotFoundException()}.jobOffers!!.toList()
        if (onlyPublished) jobOffers = jobOffers.filter { it.publicationTime == null }
        return jobOffers
    }

}