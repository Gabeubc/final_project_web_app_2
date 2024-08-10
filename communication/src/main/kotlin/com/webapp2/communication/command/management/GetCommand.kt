package com.webapp2.communication.command.management

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.jobOffer.JobOfferDto
import com.webapp2.communication.service.management.JobOfferService
import com.webapp2.communication.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired

class GetCommand(
    @Autowired
    private val jobOfferService: JobOfferService,
    private var state: String,
    private var customerId: Long,
    private var professionalId: Long
) : Command<List<JobOfferDto>> {
    override fun execute(): List<JobOfferDto> {
        if (GeneralConstant.VALID_JOB_OFFER_STATE.contains(state)) {
            if (GeneralConstant.EMPTY_ID == professionalId && GeneralConstant.EMPTY_ID != customerId)
                return jobOfferService.getJobOfferByStateForCustomer(state, customerId)
            if (GeneralConstant.EMPTY_ID == customerId && GeneralConstant.EMPTY_ID != professionalId)
                return jobOfferService.getJobOfferByStateForProfessional(state, professionalId)
        }
        return jobOfferService.getJobOffer(customerId, professionalId, state)
    }
}