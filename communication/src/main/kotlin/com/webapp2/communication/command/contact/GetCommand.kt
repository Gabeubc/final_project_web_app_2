package com.webapp2.communication.command.contact

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.service.contact.ContactService
import com.webapp2.communication.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetCommand(
    @Autowired
    private val contactService: ContactService,
    private val contactId: Long
) : Command<MutableSet<ContactDto>> {
    override fun execute(): MutableSet<ContactDto> {
        if (GeneralConstant.EMPTY_ID.equals(contactId)) {
            return contactService.getContacts().map { it.toDto() }.toMutableSet()
        } else {
            return mutableSetOf(contactService.getContactById(contactId).toDto())
        }
    }
}