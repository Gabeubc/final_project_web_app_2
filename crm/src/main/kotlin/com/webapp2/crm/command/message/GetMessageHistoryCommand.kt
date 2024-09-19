package com.webapp2.crm.command.message

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.message.MessageStateDto
import com.webapp2.crm.service.message.MessageService
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetMessageHistoryCommand(
    @Autowired
    private val messageService: MessageService
) : Command<List<MessageStateDto>> {
    private var messageId: Long = EMPTY_ID
    fun setGetMessageHistoryCommand(messageId: Long){
        this.messageId = messageId
    }
    override fun execute(): List<MessageStateDto> {
        return when(messageId){
            EMPTY_ID -> listOf()
            else -> messageService.getMessageHistory(messageId)
        }
    }

}