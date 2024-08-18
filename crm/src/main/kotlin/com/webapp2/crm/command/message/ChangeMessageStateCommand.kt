package com.webapp2.crm.command.message

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.message.MessageStateDto
import com.webapp2.crm.service.MessageService
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChangeMessageStateCommand(
    @Autowired
    private val messageService: MessageService
) : Command<Boolean>{
    private var messageId: Long = EMPTY_ID
    private var messageStateDto: MessageStateDto? = null
    fun setChangeMessageStateCommand(messageId: Long, messageStateDto: MessageStateDto){
        this.messageId = messageId
        this.messageStateDto = messageStateDto
    }
    override fun execute(): Boolean {
        return when(messageId){
            EMPTY_ID -> false
            else -> messageService.changeMessageState(messageId, messageStateDto!!)
        }
    }
}