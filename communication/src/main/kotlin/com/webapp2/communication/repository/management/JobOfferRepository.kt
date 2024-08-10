package com.webapp2.communication.repository.management

import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.entity.contact.professional.Professional
import com.webapp2.communication.entity.jobOffer.JobOffer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JobOfferRepository : JpaRepository<JobOffer, Long> {
    fun findJobOfferByState(state: String): List<JobOffer>
    fun findByCustomerAndState(customer: Contact, state: String): List<JobOffer>
    fun findByProfessionalAndState(professional: Professional, state: String): List<JobOffer>
    fun findByCustomerOrProfessionalOrState(customer: Contact, professional: Professional, state: String): List<JobOffer>
}