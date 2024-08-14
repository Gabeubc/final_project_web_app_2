package com.webapp2.communication.command.message

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.message.MessageDto
import com.webapp2.communication.dto.message.MessageStateDto
import com.webapp2.communication.service.message.MessageService
import org.springframework.beans.factory.annotation.Autowired

class GetStateCommand (
    @Autowired
    private val messageService: MessageService,
) : Command<MutableSet<MessageStateDto>> {
    private var messageId: Long = 0L
    fun setGetStateCommand(messageId: Long){
        this.messageId = messageId
    }
    override fun execute(): MutableSet<MessageStateDto> {
        return  messageService.getHistory(messageId)
    }
}