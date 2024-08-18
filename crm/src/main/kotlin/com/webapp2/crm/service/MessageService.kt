package com.webapp2.crm.service

import com.webapp2.crm.dto.message.MessageDto
import com.webapp2.crm.dto.message.MessageStateDto

interface MessageService {
    fun getMessages(): List<MessageDto>
    fun getMessageHistory(messageId: Long): List<MessageStateDto>
    fun getMessageById(messageId: Long): MessageDto
    fun createMessage(messageDto: MessageDto): Boolean
    fun changeMessageState(messageId: Long, messageStateDto: MessageStateDto): Boolean
}