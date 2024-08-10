package com.webapp2.communication.service.management

import com.webapp2.communication.dto.jobOffer.JobOfferDto
import com.webapp2.communication.entity.jobOffer.JobOffer

interface JobOfferService {
    fun getJobOfferByStateForCustomer(state: String, customerId: Long): List<JobOfferDto>
    fun getJobOfferByStateForProfessional(state: String, professionalId: Long): List<JobOfferDto>
    fun getJobOfferForCustomer(state: String): List<JobOfferDto>
    fun getJobOffer(customerId: Long, professionalId: Long, state: String): List<JobOfferDto>
    fun saveJobOffer(jobOffer: JobOffer): Boolean

}