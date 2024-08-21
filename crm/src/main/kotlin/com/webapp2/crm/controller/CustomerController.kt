package com.webapp2.crm.controller

import com.webapp2.crm.command.customer.GetCustomerCommand
import com.webapp2.crm.command.customer.GetCustomerJobOfferCommand
import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_STRING
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping(value=["/API/customers"])
class CustomerController(
    @Autowired
    private val beanFactory: BeanFactory
) {
    @GetMapping(value = [""])
    fun getCustomers(
        @RequestParam(name="value") value: String
        //add request params
        ): List<ContactDto>{
        val command: GetCustomerCommand = beanFactory.getBean(GetCustomerCommand::class.java)
        command.setGetCustomerCommand(EMPTY_ID, value)
        return command.execute()
    }
    @GetMapping(value = ["/{customerId}"])
    fun getCustomersById(
        @PathVariable(name="customerId") customerId: Long,
    ): List<ContactDto>{
        val command: GetCustomerCommand = beanFactory.getBean(GetCustomerCommand::class.java)
        command.setGetCustomerCommand(customerId, EMPTY_STRING)
        return command.execute()
    }
    @GetMapping(value = ["/{customerId}/jobOffers"])
    fun getJobOffersByCustomer(
        @PathVariable(name="customerId") customerId: Long,
        @RequestParam(name="professionalName") professionalName: String,
        @RequestParam(name="onlyPublished") onlyPublished: Boolean,
        @RequestParam(name="skills") skills: List<String>,
        @RequestParam(name="states") states: List<String>,
        @RequestParam(name="creationTime") creationTime: LocalDateTime
    ): List<JobOfferDto> {
        val command: GetCustomerJobOfferCommand = beanFactory.getBean(GetCustomerJobOfferCommand::class.java)
        command.setGetCustomerJobOfferCommand(
            customerId,
            professionalName,
            onlyPublished,
            skills,
            states,
            creationTime
        )
        return command.execute()
    }
}