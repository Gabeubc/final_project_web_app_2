package com.webapp2.communication.command.contact

import com.webapp2.communication.command.Command
import com.webapp2.communication.service.contact.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeleteCommand(
    @Autowired
    private val contactService: ContactService,
    private val contactId: Long
) : Command<Boolean>{
    override fun execute(): Boolean {
        return contactService.deleteContact(contactId)
    }
}