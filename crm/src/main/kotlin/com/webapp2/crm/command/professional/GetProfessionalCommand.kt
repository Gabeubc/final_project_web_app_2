package com.webapp2.crm.command.professional

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.service.ProfessionalService
import com.webapp2.crm.utils.GeneralConstant
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_STRING
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class GetProfessionalCommand(
    @Autowired
    private val professionalService: ProfessionalService
) : Command<List<ContactDto>> {
    private var professionalId: Long = EMPTY_ID
    private var value: String = EMPTY_STRING

    fun setGetProfessionalCommand(
        professionalId: Long,
        value: String
    ) {
        this.professionalId = professionalId
        this.value = value
    }

    override fun execute(): List<ContactDto> {
        return when(professionalId){
            EMPTY_ID -> {
                when(value.isEmpty()){
                    true -> professionalService.getProfessionals()
                    else -> professionalService.getProfessionalsByNameOrSurname(value)
                }
            }
            else -> listOf(professionalService.getProfessionalById(professionalId))
        }
    }
}