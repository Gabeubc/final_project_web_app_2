package com.webapp2.crm.command.message

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.message.MessageDto
import com.webapp2.crm.service.message.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateMessageCommand(
    @Autowired
    private val messageService: MessageService
) : Command<Boolean> {
    private var messageDto: MessageDto? = null
    fun setCreateMessageCommand(messageDto: MessageDto){
        this.messageDto = messageDto
    }
    override fun execute(): Boolean {
        return when(messageDto == null){
            true -> false
            else -> messageService.createMessage(messageDto!!)
        }
    }
}