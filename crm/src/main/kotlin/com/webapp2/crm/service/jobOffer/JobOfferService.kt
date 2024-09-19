package com.webapp2.crm.service.jobOffer

import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.dto.jobOffer.JobOfferStateDto

interface JobOfferService {
    fun getJobOffersByStateAndCustomerId(customerId: Long, state:String): List<JobOfferDto>
    fun getJobOfferByStateAndProfessional(professionalId: Long, state:String): List<JobOfferDto>
    fun getJobOfferByState(state: String): List<JobOfferDto>
    fun getJobOffers(): List<JobOfferDto>
    fun changeJobOfferState(jobOfferId: Long, jobOfferStateDto: JobOfferStateDto): Boolean
    fun getJobOfferValue(jobOfferId: Long): Double
    fun createJobOffer(jobOfferDto: JobOfferDto): Boolean
}