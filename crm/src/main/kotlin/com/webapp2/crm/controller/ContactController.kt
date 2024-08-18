package com.webapp2.crm.controller

import com.webapp2.crm.command.contact.AddEmailCommand
import com.webapp2.crm.command.contact.CreateContactCommand
import com.webapp2.crm.command.contact.DeleteEmailCommand
import com.webapp2.crm.command.contact.GetContactCommand
import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.hibernate.sql.Delete
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value=["/API/Contacts"])
class ContactController(
    @Autowired
    private val beanFactory: BeanFactory
) {
    @GetMapping(value = [""])
    fun getContacts(
        // add request params
    ): List<ContactDto>{
        val command: GetContactCommand = beanFactory.getBean(GetContactCommand::class.java)
        command.setGetCommand(EMPTY_ID)
        return command.execute()
    }
    @GetMapping(value= ["/{contactId}"])
    fun getContactById(
        @PathVariable(name="contactId") contactId: Long
    ): List<ContactDto>{
        val command: GetContactCommand = beanFactory.getBean(GetContactCommand::class.java)
        command.setGetCommand(contactId)
        return command.execute()
    }
    @PostMapping(value=[""])
    fun createContact(
        @RequestBody contactDto: ContactDto
    ): Boolean{
        val command: CreateContactCommand = beanFactory.getBean(CreateContactCommand::class.java)
        command.setCreateContactCommand(contactDto)
        return command.execute()
    }
    @PostMapping(value=["/{contactId}/email"])
    fun addEmail(
        @PathVariable(name="contactId") contactId: Long,
        @RequestBody emails: List<String>
    ): Boolean{
        val command: AddEmailCommand = beanFactory.getBean(AddEmailCommand::class.java)
        command.setAddEmailCommand(contactId, emails)
        return command.execute()
    }
    @DeleteMapping(value=["/{contactId}/email/{email}"])
    fun deleteEmail(
        @PathVariable(name = "email") email: String,
        @PathVariable(name = "contactId") contactId: Long
    ): Boolean{
        val command: DeleteEmailCommand = beanFactory.getBean(DeleteEmailCommand::class.java)
        command.setAddEmailCommand(contactId, email)
        return command.execute()
    }
}