package com.webapp2.crm.controller

import com.webapp2.crm.command.professional.GetProfessionalCommand
import com.webapp2.crm.dto.contact.ContactDto
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/API/professionals"])
class ProfessionalController(
    @Autowired
    private val beanFactory: BeanFactory
) {
    @GetMapping(value = ["/"])
    fun getProfessionals(
        //add request params
    ): List<ContactDto> {
        val command: GetProfessionalCommand = beanFactory.getBean(GetProfessionalCommand::class.java)
        return command.execute()
    }
}