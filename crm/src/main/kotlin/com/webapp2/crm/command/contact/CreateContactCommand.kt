package com.webapp2.crm.command.contact

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.service.ContactService
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateContactCommand(
    @Autowired
    private val contactService: ContactService
) : Command<Boolean> {
    private var contactDto: ContactDto? = null
    fun setCreateContactCommand(contactDto: ContactDto) {
        this.contactDto = contactDto
    }

    override fun execute(): Boolean {
        return when(val id = contactService.createContact(contactDto!!)){
            EMPTY_ID -> false
            else -> {
                return when(contactDto!!.type){
                    "operator" -> contactService.assignToOperator(id)
                    "customer" -> contactService.assignToCustomer(id)
                    "professional" -> contactService.assignToProfessional(id)
                    else -> false
                }
            }
        }
    }
}