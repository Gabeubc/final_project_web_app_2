package com.webapp2.crm.controller

import com.webapp2.crm.command.jobOffer.ChangeJobOfferStateCommand
import com.webapp2.crm.command.jobOffer.CreateJobOfferCommand
import com.webapp2.crm.command.jobOffer.GetJobOfferCommand
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.dto.jobOffer.JobOfferStateDto
import com.webapp2.crm.dto.message.MessageStateDto
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/API/jobOffers"])
class JobOfferController(
    @Autowired
    private val beanFactory: BeanFactory
) {
    @GetMapping(value = [""])
    fun getJobOffers(
        // add request params
    ): List<JobOfferDto> {
        val command: GetJobOfferCommand = beanFactory.getBean(GetJobOfferCommand::class.java)
        return command.execute()
    }

    @PostMapping(value = [""])
    fun createJobOffer(
        @RequestBody jobOfferDto: JobOfferDto
    ): Boolean {
        val command: CreateJobOfferCommand = beanFactory.getBean(CreateJobOfferCommand::class.java)
        command.setCreateJobOfferCommand(jobOfferDto)
        return command.execute()
    }

    @PostMapping(value = ["/{jobOfferId}"])
    fun changeJobOfferState(
        @PathVariable(name = "jobOfferId") jobOfferId: Long,
        @RequestBody state: JobOfferStateDto
    ): Boolean {
        val command: ChangeJobOfferStateCommand = beanFactory.getBean(ChangeJobOfferStateCommand::class.java)
        command.setChangeJobOfferStateCommand(jobOfferId, state)
        return command.execute()
    }
}