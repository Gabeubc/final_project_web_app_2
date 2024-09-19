package com.webapp2.crm.command.contact

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.security.RegistrationService
import com.webapp2.crm.service.contact.ContactService
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateContactCommand(
    @Autowired
    private val contactService: ContactService,
    @Autowired
    private val registrationService: RegistrationService
) : Command<Long> {
    private var contactDto: ContactDto? = null
    fun setCreateContactCommand(contactDto: ContactDto) {
        this.contactDto = contactDto
    }

    @Transactional
    override fun execute(): Long {
        registrationService.createUser(contactDto!!)
        return when(contactDto!!.id != null ){
            true ->
                return when(val id = contactService.createContact(contactDto!!)){
                    EMPTY_ID -> EMPTY_ID
                    else -> {
                        return when(contactDto!!.type){
                            "operator" -> contactService.assignToOperator(id)
                            "customer" -> contactService.assignToCustomer(id)
                            "professional" -> contactService.assignToProfessional(id)
                            else -> EMPTY_ID
                        }
                    }
                }
            false -> EMPTY_ID
        }
    }

}