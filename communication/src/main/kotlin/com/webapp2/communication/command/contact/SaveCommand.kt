package com.webapp2.communication.command.contact

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.service.contact.ContactService
import com.webapp2.communication.service.message.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SaveCommand(
    @Autowired
    private val contactService: ContactService,
    private val contactDto: ContactDto
) : Command<Boolean> {
    override fun execute(): Boolean {
        return contactService.saveContact(contactDto.toEntity())
    }
}