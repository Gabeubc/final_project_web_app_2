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
    private val messageService: MessageService
) : Command<MutableSet<MessageDto>> {
    private var messageId: Long = 0L
    fun setGetCommand(messageId: Long){
        this.messageId = messageId
    }
    override fun execute(): MutableSet<MessageDto> {
        if (GeneralConstant.EMPTY_ID == messageId) {
            return messageService.getMessages()
        } else {
            return mutableSetOf(messageService.getMessageById(messageId))
        }
    }
}