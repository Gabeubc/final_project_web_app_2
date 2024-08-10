package com.webapp2.communication.command.message

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.message.MessageDto
import com.webapp2.communication.service.message.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SaveCommand(
    @Autowired
    private val messageService: MessageService,
    private val messageDto: MessageDto
) : Command<Boolean> {
    override fun execute(): Boolean {
        return messageService.saveMessage(messageDto.toEntity())
    }
}