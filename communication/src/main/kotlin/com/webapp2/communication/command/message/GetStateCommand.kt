package com.webapp2.communication.command.message

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.message.MessageStateDto
import com.webapp2.communication.service.message.MessageService
import org.springframework.beans.factory.annotation.Autowired

class GetStateCommand (
    @Autowired
    private val messageService: MessageService,
    private val messageId: Long
): Command<MutableSet<MessageStateDto>> {
    override fun execute(): MutableSet<MessageStateDto> {
        return  messageService.getHistory(messageId)
    }
}