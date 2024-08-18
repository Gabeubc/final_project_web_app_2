package com.webapp2.crm.repository

import com.webapp2.crm.entity.contact.Customer
import com.webapp2.crm.entity.contact.Professional
import com.webapp2.crm.entity.jobOffer.JobOffer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JobOfferRepository : JpaRepository<JobOffer, Long>{
    fun findByCustomer(customer: Customer): List<JobOffer>
    fun findByProfessional(professional: Professional): List<JobOffer>
}