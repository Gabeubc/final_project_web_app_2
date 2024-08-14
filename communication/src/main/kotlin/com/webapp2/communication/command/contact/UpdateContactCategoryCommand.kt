package com.webapp2.communication.command.contact

import com.webapp2.communication.command.Command
import com.webapp2.communication.service.contact.ContactService
import org.springframework.beans.factory.annotation.Autowired

class UpdateCategoryCommand(
    @Autowired
    private val contactService: ContactService
) : Command<Boolean>{
    private var contactId: Long = 0L
    private var category: String = ""
    fun setUpdateCategoryCommand(contactId: Long, category: String){
        this.contactId = contactId
        this.category = category
    }

    override fun execute(): Boolean {
        return contactService.updateCategory(contactId, category)
    }
}