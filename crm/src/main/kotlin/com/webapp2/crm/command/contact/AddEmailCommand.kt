package com.webapp2.crm.command.contact

import com.webapp2.crm.command.Command
import com.webapp2.crm.service.contact.ContactService
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AddEmailCommand(
    @Autowired
    private var contactService: ContactService
) : Command<Boolean> {
    private var contactId: Long = EMPTY_ID
    private var emails: List<String> = listOf()
    fun setAddEmailCommand(contactId: Long, emails: List<String>) {
        this.contactId = contactId
        this.emails = emails
    }

    override fun execute(): Boolean {
        return when (emails.isEmpty()) {
            true -> true
            else -> contactService.addEmail(contactId, emails)
        }
    }
}