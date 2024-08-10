package com.webapp2.communication.controller

import com.webapp2.communication.command.management.GetCommand
import com.webapp2.communication.dto.jobOffer.JobOfferDto
import com.webapp2.communication.utils.GeneralConstant
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

class ManagementController(
    @Autowired
    private val beanFactory: BeanFactory
) {
    @GetMapping(
        value = ["/API/jobOffers/open/{customerId}"]
    )
    fun getOpennedJobOfferByCustomer(
        @PathVariable(name = "customerId") customerId: Long
    ): List<JobOfferDto> {
        val commandCreated: GetCommand =
            beanFactory.getBean(GetCommand::class.java, GeneralConstant.CREATED, customerId, GeneralConstant.EMPTY_ID)
        val commandSelection: GetCommand =
            beanFactory.getBean(GetCommand::class.java, GeneralConstant.SELECTION, customerId, GeneralConstant.EMPTY_ID)
        val commandProposal: GetCommand =
            beanFactory.getBean(GetCommand::class.java, GeneralConstant.PROPOSAL, customerId, GeneralConstant.EMPTY_ID)
        return listOf(
            commandCreated.execute(),
            commandSelection.execute(),
            commandProposal.execute()
        ).flatten()
    }

    @GetMapping(
        value = ["/API/jobOffers/accepted/{professionalId}"]
    )
    fun getAccepetedJobOfferByProfessional(
        @PathVariable(name = "professionalId") professionalId: Long
    ): List<JobOfferDto> {
        val commandCreated: GetCommand =
            beanFactory.getBean(GetCommand::class.java, GeneralConstant.DONE, GeneralConstant.EMPTY_ID, professionalId)
        return commandCreated.execute()
    }

    @GetMapping(
        value = ["/API/jobOffers/aborted"]
    )
    fun getAbortedJobOfferByProfessional(
        @PathVariable(name = "professionalId") professionalId: Long
    ): List<JobOfferDto> {
        val commandCreated: GetCommand =
            beanFactory.getBean(GetCommand::class.java, GeneralConstant.ABORTED, GeneralConstant.EMPTY_ID, GeneralConstant.EMPTY_ID)
        return commandCreated.execute()
    }
}