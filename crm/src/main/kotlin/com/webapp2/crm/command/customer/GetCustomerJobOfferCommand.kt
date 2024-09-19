package com.webapp2.crm.command.customer

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.service.customer.CustomerService
import com.webapp2.crm.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class GetCustomerJobOfferCommand(
    @Autowired
    private val customerService: CustomerService
): Command<List<JobOfferDto>> {
    private var customerId: Long = GeneralConstant.EMPTY_ID
    private var professionalName: String = GeneralConstant.EMPTY_STRING
    private var onlyPublished: Boolean = false
    private var skills: List<String> = listOf()
    private var states: List<String> = listOf()
    private var creationTime: LocalDateTime? = null

    fun setGetCustomerJobOfferCommand(
        customerId: Long,
        professionalName: String,
        onlyPublished: Boolean,
        skills: List<String>,
        states: List<String>,
        creationTime: LocalDateTime?
    ) {
        this.customerId = customerId
        this.professionalName = professionalName
        this.onlyPublished = onlyPublished
        this.skills = skills
        this.states = states
        this.creationTime = creationTime
    }
    override fun execute(): List<JobOfferDto> {
        val dtos : MutableList<JobOfferDto> = mutableListOf()
        if(customerId != GeneralConstant.EMPTY_ID){
            if(professionalName != GeneralConstant.EMPTY_STRING)
                dtos.addAll(customerService.getCustomerJobOffersByProfessional(onlyPublished, professionalName, customerId))
            if(skills.isNotEmpty())
                dtos.addAll(customerService.getCustomerJobOffersBySkills(onlyPublished,skills, customerId))
            if(states.isNotEmpty())
                dtos.addAll(customerService.getCustomerJobOffersByState(onlyPublished, states, customerId))
            if(creationTime != null)
                dtos.addAll(customerService.getCustomerJobOffersByCreationTime(onlyPublished, creationTime!!, customerId))
        }
        return dtos
    }
}