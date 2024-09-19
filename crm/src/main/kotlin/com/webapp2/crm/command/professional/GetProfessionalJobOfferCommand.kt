package com.webapp2.crm.command.professional

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.service.professional.ProfessionalService
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_STRING
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class GetProfessionalJobOfferCommand(
    @Autowired
    private val professionalService: ProfessionalService
) : Command<List<JobOfferDto>> {
    private var professionalId: Long = EMPTY_ID
    private var customerName: String = EMPTY_STRING
    private var onlyPublished: Boolean = false
    private var skills: List<String> = listOf()
    private var states: List<String> = listOf()
    private var creationTime: LocalDateTime? = null

    fun setGetProfessionalJobOfferCommand(
        professionalId: Long,
        customerName: String,
        onlyPublished: Boolean,
        skills: List<String>,
        states: List<String>,
        creationTime: LocalDateTime?
    ) {
        this.professionalId = professionalId
        this.customerName = customerName
        this.onlyPublished = onlyPublished
        this.skills = skills
        this.states = states
        this.creationTime = creationTime
    }
    override fun execute(): List<JobOfferDto> {
        val dtos : MutableList<JobOfferDto> = mutableListOf()
        if(professionalId != EMPTY_ID){
            if(customerName != EMPTY_STRING)
                dtos.addAll(professionalService.getProfessionalJobOffersByCustomer(onlyPublished, customerName, professionalId))
            if(skills.isNotEmpty())
                dtos.addAll(professionalService.getProfessionalJobOffersBySkills(onlyPublished,skills, professionalId))
            if(states.isNotEmpty())
                dtos.addAll(professionalService.getProfessionalJobOffersByState(onlyPublished, states, professionalId))
            if(creationTime != null)
                dtos.addAll(professionalService.getProfessionalJobOffersByCreationTime(onlyPublished, creationTime!!, professionalId))
        }
        return dtos
    }
}