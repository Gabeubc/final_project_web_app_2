package com.webapp2.communication.command.management

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.jobOffer.JobOfferDto
import com.webapp2.communication.service.management.JobOfferService
import org.springframework.beans.factory.annotation.Autowired

class SaveCommand(
    @Autowired
    private val jobOfferService: JobOfferService,
    private val jobOfferDto: JobOfferDto
) : Command<Boolean> {
    override fun execute(): Boolean {
        return jobOfferService.saveJobOffer(jobOfferDto.toEntity())
    }
}