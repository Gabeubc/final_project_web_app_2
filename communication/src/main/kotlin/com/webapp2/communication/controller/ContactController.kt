package com.webapp2.communication.controller

import com.webapp2.communication.command.contact.DeleteCommand
import com.webapp2.communication.command.contact.GetCommand
import com.webapp2.communication.command.contact.SaveCommand
import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.service.contact.ContactService
import com.webapp2.communication.utils.GeneralConstant
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(name = "/API/contacts")
class ContactController(
    @Autowired
    private val beanFactory: BeanFactory
) {
    @GetMapping(name = "/")
    fun getContact(): MutableSet<ContactDto>{
        val command: GetCommand = beanFactory.getBean(GetCommand::class.java, GeneralConstant.EMPTY_ID)
        return command.execute()
    }
    @GetMapping(name = "/{contactId}")
    fun getContactById(
        @PathVariable(name = "contactId") contactId: Long
    ): MutableSet<ContactDto>{
        val command: GetCommand = beanFactory.getBean(GetCommand::class.java, contactId)
        return command.execute()
    }
    @PostMapping(name = "/")
    fun createContact(
        @RequestBody contactDto: ContactDto
    ): Boolean{
        val command: SaveCommand = beanFactory.getBean(SaveCommand::class.java, contactDto)
        return command.execute()
    }
    @PostMapping(name = "/{contactId}/email")
    fun addEmail(
        @PathVariable(name = "contactId") contactId: Long,
        @RequestBody emails: List<String>
    ): Boolean{
        val command: SaveCommand = beanFactory.getBean(SaveCommand::class.java, emails)
        return command.execute()
    }
    @PutMapping(name = "/{contactId}/category")
    fun updateCategory(
        @PathVariable(name = "contactId") contactId: Long,
        @RequestBody category: String
    ): Boolean{
        val command: SaveCommand = beanFactory.getBean(SaveCommand::class.java, category)
        return command.execute()
    }
    @PutMapping(name = "/{contactId}/address/{addressId}")
    fun updateAddress(
        @PathVariable(name = "contactId") contactId: Long,
        @PathVariable(name = "addressId") emailId: Long,
        @RequestBody category: String
    ): Boolean{
        val command: SaveCommand = beanFactory.getBean(SaveCommand::class.java, category)
        return command.execute()
    }
    @DeleteMapping(name = "/{contactId}/email/{emailId}")
    fun updateEmail(
    @PathVariable(name = "contactId") contactId: Long,
    @PathVariable(name = "emailId") emailId: Long,
    ): Boolean{
        val command: DeleteCommand = beanFactory.getBean(DeleteCommand::class.java, contactId)
        return command.execute()
    }
}