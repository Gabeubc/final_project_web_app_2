package com.webapp2.crm.command.jobOffer

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.service.jobOffer.JobOfferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetJobOfferCommand(
    @Autowired
    private val jobOfferService: JobOfferService
) : Command<List<JobOfferDto>> {
    override fun execute(): List<JobOfferDto> {
        return jobOfferService.getJobOffers()
    }
}