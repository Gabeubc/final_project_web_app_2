package com.webapp2.communication.command.contact

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.service.contact.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SaveCommand(
    @Autowired
    private val contactService: ContactService
) : Command<Boolean> {
    private lateinit var  contactDto: ContactDto
    fun setSaveCommand(contactDto: ContactDto){
        this.contactDto = contactDto
    }
    override fun execute(): Boolean {
        return contactService.saveContact(contactDto)
    }
}