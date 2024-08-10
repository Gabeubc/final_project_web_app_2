package com.webapp2.communication.service.message

import com.webapp2.communication.dto.message.MessageDto
import com.webapp2.communication.dto.message.MessageStateDto
import com.webapp2.communication.entity.message.Message
import com.webapp2.communication.entity.message.MessageState

interface MessageService {
    fun getMessages(): MutableSet<MessageDto>
    fun getMessageById(messageId: Long): MessageDto
    fun saveMessage(message: Message): Boolean
    fun updateStateMessage(message: MessageState): Boolean
    fun getHistory(messageId: Long): MutableSet<MessageStateDto>
    fun changeMessagePriority(): Boolean
}