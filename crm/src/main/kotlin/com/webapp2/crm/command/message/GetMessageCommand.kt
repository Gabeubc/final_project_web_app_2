package com.webapp2.crm.command.message

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.message.MessageDto
import com.webapp2.crm.service.MessageService
import com.webapp2.crm.utils.GeneralConstant
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetMessageCommand(
    @Autowired
    private val messageService: MessageService
) : Command<List<MessageDto>>{
    private var messageId: Long = GeneralConstant.EMPTY_ID
    fun setGetMessageByIdCommand(messageId: Long){
        this.messageId = messageId
    }
    override fun execute(): List<MessageDto> {
        return when(messageId){
            EMPTY_ID -> messageService.getMessages()
            else -> listOf(messageService.getMessageById(messageId))
        }
    }
}