package com.webapp2.communication.controller

import com.webapp2.communication.command.message.GetCommand
import com.webapp2.communication.command.message.GetStateCommand
import com.webapp2.communication.command.message.SaveCommand
import com.webapp2.communication.dto.message.MessageDto
import com.webapp2.communication.dto.message.MessageStateDto
import com.webapp2.communication.service.message.MessageService
import com.webapp2.communication.utils.GeneralConstant
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(name = "/API/messages")
class MessageController(
    @Autowired
    private val beanFactory: BeanFactory
) {
    @GetMapping(name = "/")
    fun getMessage(): MutableSet<MessageDto> {
        val command: GetCommand = beanFactory.getBean(GetCommand::class.java, GeneralConstant.EMPTY_ID)
        return command.execute()
    }
    @GetMapping(name = "/{messageId}")
    fun getMessageById(
        @PathVariable(name = "messageId") messageId: Long
    ): MutableSet<MessageDto> {
        val command: GetCommand = beanFactory.getBean(GetCommand::class.java, messageId)
        return command.execute()
    }
    @PostMapping(name="/")
    fun createDocument(
        @RequestBody messageDto: MessageDto
    ):Boolean{
        val command: SaveCommand = beanFactory.getBean(SaveCommand::class.java, messageDto)
        return command.execute()
    }
    @GetMapping(name = "/{messageId}/history")
    fun getMessageHistoryById(
        @PathVariable(name = "messageId") messageId: Long
    ): MutableSet<MessageStateDto> {
        val command: GetStateCommand = beanFactory.getBean(GetStateCommand::class.java, messageId)
        return command.execute()
    }
    /*
    @PutMapping(name = "/{messageId}/history")
    fun updateMessagePriority(
        @PathVariable(name = "messageId") messageId: Long
    ): MutableSet<MessageStateDto> {
        val command: GetStateCommand = beanFactory.getBean(GetStateCommand::class.java, messageId)
        return command.execute();
    }
    */
}