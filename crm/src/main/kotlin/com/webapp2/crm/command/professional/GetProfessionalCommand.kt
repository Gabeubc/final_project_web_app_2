package com.webapp2.crm.command.professional

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.service.ProfessionalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetProfessionalCommand(
     @Autowired
    private val professionalService: ProfessionalService
) : Command<List<ContactDto>> {
    override fun execute(): List<ContactDto> {
        return professionalService.getProfessionals()
    }
}