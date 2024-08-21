package com.webapp2.crm.controller

import com.webapp2.crm.command.professional.GetProfessionalCommand
import com.webapp2.crm.command.professional.GetProfessionalJobOfferCommand
import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.utils.GeneralConstant
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_STRING
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping(value = ["/API/professionals"])
class ProfessionalController(
    @Autowired
    private val beanFactory: BeanFactory
) {
    @GetMapping(value = [""])
    fun getProfessionals(
        @RequestParam(name="value") value: String
        //add request params
    ): List<ContactDto> {
        val command: GetProfessionalCommand = beanFactory.getBean(GetProfessionalCommand::class.java)
        command.setGetProfessionalCommand(EMPTY_ID, value)
        return command.execute()
    }
    @GetMapping(value = ["/{professionalId}"])
    fun getProfessionalsById(
        @PathVariable(name="professionalId") professionalId: Long
    ): List<ContactDto> {
        val command: GetProfessionalCommand = beanFactory.getBean(GetProfessionalCommand::class.java)
        command.setGetProfessionalCommand(professionalId, EMPTY_STRING)
        return command.execute()
    }
    @GetMapping(value = ["/{professionalId}/jobOffers"])
    fun getJobOffersByProfessional(
        @PathVariable(name="professionalId") professionalId: Long,
        @RequestParam(name="customerName") customerName: String,
        @RequestParam(name="onlyPublished") onlyPublished: Boolean,
        @RequestParam(name="skills") skills: List<String>,
        @RequestParam(name="states") states: List<String>,
        @RequestParam(name="creationTime") creationTime: LocalDateTime
        //add request params
    ): List<JobOfferDto> {
        val command: GetProfessionalJobOfferCommand = beanFactory.getBean(GetProfessionalJobOfferCommand::class.java)
        command.setGetProfessionalJobOfferCommand(
            professionalId,
            customerName,
            onlyPublished,
            skills,
            states,
            creationTime
        )
        return command.execute()
    }
}