package com.webapp2.communication.service.management

import com.webapp2.communication.dto.jobOffer.JobOfferDto
import com.webapp2.communication.entity.jobOffer.JobOffer
import com.webapp2.communication.repository.contact.ContactRepository
import com.webapp2.communication.repository.management.JobOfferRepository
import com.webapp2.communication.repository.management.ProfessionalRepository
import com.webapp2.communication.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired

class JobServiceImpl(
    @Autowired
    private val jobOfferRepository: JobOfferRepository,
    @Autowired
    private val contactRepository: ContactRepository,
    @Autowired
    private val professionalRepository: ProfessionalRepository
) : JobOfferService {
    override fun getJobOfferByStateForCustomer(state: String, customerId: Long): List<JobOfferDto> {
        val customer = contactRepository
            .findById(customerId).orElse(null)
        return jobOfferRepository
            .findByCustomerAndState(customer, state)
            .map { it.toDto() }
    }

    override fun getJobOfferByStateForProfessional(state: String, professionalId: Long): List<JobOfferDto> {
        val professional = professionalRepository
            .findById(professionalId).orElse(null)
        return jobOfferRepository
            .findByProfessionalAndState(professional, state)
            .map { it.toDto() }
    }

    override fun getJobOfferForCustomer(state: String): List<JobOfferDto> {
        return jobOfferRepository
            .findJobOfferByState(state)
            .map { it.toDto() }
    }

    override fun getJobOffer(customerId: Long, professionalId: Long, state: String): List<JobOfferDto> {
        val customer = contactRepository
            .findById(customerId)
            .orElse(null)
        val professional = professionalRepository
            .findById(professionalId)
            .orElse(null)
        return jobOfferRepository
            .findByCustomerOrProfessionalOrState(customer, professional, state)
            .map { it.toDto() }
    }

    override fun saveJobOffer(jobOffer: JobOffer): Boolean {
        try{
            jobOfferRepository
                .save(jobOffer)
            return true
        }catch (e:Exception){
            return false
        }
    }
}