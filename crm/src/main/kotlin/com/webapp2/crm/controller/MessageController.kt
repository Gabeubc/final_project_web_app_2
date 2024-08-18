package com.webapp2.crm.controller

import com.webapp2.crm.command.message.ChangeMessageStateCommand
import com.webapp2.crm.command.message.CreateMessageCommand
import com.webapp2.crm.command.message.GetMessageCommand
import com.webapp2.crm.command.message.GetMessageHistoryCommand
import com.webapp2.crm.dto.message.MessageDto
import com.webapp2.crm.dto.message.MessageStateDto
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/API/messages"])
class MessageController(
    @Autowired
    private val beanFactory: BeanFactory
) {
    @GetMapping(value=[""])
    fun getMessages(
        // add request param
    ): List<MessageDto>{
        val command: GetMessageCommand = beanFactory.getBean(GetMessageCommand::class.java)
        return command.execute()
    }
    @PostMapping(value=[""])
    fun createMessage(
        @RequestBody messageDto: MessageDto
    ): Boolean{
        val command: CreateMessageCommand = beanFactory.getBean(CreateMessageCommand::class.java)
        command.setCreateMessageCommand(messageDto)
        return command.execute()
    }
    @GetMapping(value = ["/{messageId}"])
    fun getMessageById(
        @PathVariable(name = "messageId") messageId: Long
    ): List<MessageDto>{
        val command: GetMessageCommand = beanFactory.getBean(GetMessageCommand::class.java)
        command.setGetMessageByIdCommand(messageId)
        return command.execute()
    }
    @PostMapping(value = ["/{messageId}"])
    fun changeMessageState(
        @PathVariable(name = "messageId") messageId: Long,
        @RequestBody messageStateDto: MessageStateDto
    ): Boolean{
        val command : ChangeMessageStateCommand = beanFactory.getBean(ChangeMessageStateCommand::class.java)
        command.setChangeMessageStateCommand(messageId, messageStateDto)
        return command.execute()
    }
    @GetMapping(value = ["/{messageId}/history"])
    fun getMessageHistory(
        @PathVariable(name = "messageId") messageId: Long
    ): List<MessageStateDto>{
        val command: GetMessageHistoryCommand = beanFactory.getBean(GetMessageHistoryCommand::class.java)
        command.setGetMessageHistoryCommand(messageId)
        return command.execute()
    }
}