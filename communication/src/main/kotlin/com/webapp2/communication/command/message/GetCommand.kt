package com.webapp2.communication.command.message

import com.webapp2.communication.command.Command
import com.webapp2.communication.dto.message.MessageDto
import com.webapp2.communication.service.message.MessageService
import com.webapp2.communication.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetCommand(
    @Autowired
    private val messageService: MessageService,
    private val messageId: Long
) : Command<MutableSet<MessageDto>> {
    override fun execute(): MutableSet<MessageDto> {
        if (GeneralConstant.EMPTY_ID.equals(messageId)) {
            return messageService.getMessages()
        } else {
            return mutableSetOf(messageService.getMessageById(messageId))
        }
    }
}