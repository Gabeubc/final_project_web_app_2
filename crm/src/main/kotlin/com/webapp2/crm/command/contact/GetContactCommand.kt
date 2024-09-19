package com.webapp2.crm.command.contact

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.service.contact.ContactService
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetContactCommand(
    @Autowired
    private val contactService: ContactService
) : Command<List<ContactDto>> {
    private var contactId: Long = EMPTY_ID
    fun setGetCommand(contactId: Long) {
        this.contactId = contactId
    }

    override fun execute(): List<ContactDto> {
        return when (contactId) {
            EMPTY_ID -> contactService.getContacts()
            else -> listOf(contactService.getContactById(contactId))
        }
    }
}