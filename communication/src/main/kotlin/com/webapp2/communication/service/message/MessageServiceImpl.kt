package com.webapp2.communication.service.message

import com.webapp2.communication.dto.message.MessageDto
import com.webapp2.communication.dto.message.MessageStateDto
import com.webapp2.communication.entity.message.Message
import com.webapp2.communication.entity.message.MessageState
import com.webapp2.communication.repository.message.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    @Autowired
    private val messageRepository: MessageRepository
) : MessageService {
    override fun getMessages(): MutableSet<MessageDto> {
        return messageRepository.findAll().map { it.toDto() }.toMutableSet()
    }

    override fun getMessageById(messageId: Long): MessageDto {
        return messageRepository.findById(messageId).map { it.toDto() }.orElse(null)
    }

    override fun saveMessage(message: Message): Boolean {
        try {
            messageRepository.save(message)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun updateStateMessage(messageState: MessageState): Boolean {
        try {
            var entity = messageRepository.findById(messageState.message.id!!).orElse(null)
            entity
                .messageState
                .add(messageState)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun getHistory(messageId: Long): MutableSet<MessageStateDto> {
       return messageRepository.findById(messageId).orElse(null).messageState.map { it.toDto() }.toMutableSet()
    }

    override fun changeMessagePriority(): Boolean {
        TODO()
    }
}