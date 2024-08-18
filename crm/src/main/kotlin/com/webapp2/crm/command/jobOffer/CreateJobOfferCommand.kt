package com.webapp2.crm.command.jobOffer

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.service.JobOfferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateJobOfferCommand(
    @Autowired
    private val jobOfferService: JobOfferService
) : Command<Boolean> {
    private var jobOfferDto: JobOfferDto? = null
    fun setCreateJobOfferCommand(jobOfferDto: JobOfferDto){
        this.jobOfferDto = jobOfferDto
    }
    override fun execute(): Boolean {
        return when(jobOfferDto){
            null -> false
            else -> jobOfferService.createJobOffer(jobOfferDto!!)
        }
    }

}