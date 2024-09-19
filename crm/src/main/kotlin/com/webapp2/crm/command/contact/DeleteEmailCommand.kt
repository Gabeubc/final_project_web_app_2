package com.webapp2.crm.command.contact

import com.webapp2.crm.command.Command
import com.webapp2.crm.service.contact.ContactService
import com.webapp2.crm.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeleteEmailCommand(
    @Autowired
    private var contactService: ContactService
) : Command<Boolean> {
    private var contactId: Long = GeneralConstant.EMPTY_ID
    private var email: String = "listOf()"
    fun setAddEmailCommand(contactId: Long, email: String) {
        this.contactId = contactId
        this.email = email
    }
    override fun execute(): Boolean {
        return when (email.isEmpty()) {
            true -> true
            else -> contactService.deleteEmail(contactId, email)
        }
    }
}