package com.webapp2.communication.command.contact

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.service.contact.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeleteCommand(
    @Autowired
    private val contactService: ContactService
) : Command<Boolean> {

    private var contactId: Long = 0
    private var emailId: String = ""
    fun setContactId(contactId: Long, emailId: String){
        this.contactId = contactId
        this.emailId = emailId
    }
    override fun execute(): Boolean {
        return contactService.deleteContact(contactId)
    }
}