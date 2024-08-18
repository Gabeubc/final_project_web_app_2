package com.webapp2.crm.service

import com.webapp2.crm.dto.message.MessageDto
import com.webapp2.crm.dto.message.MessageStateDto
import com.webapp2.crm.entity.message.Message
import com.webapp2.crm.entity.message.MessageState
import com.webapp2.crm.repository.ContactRepository
import com.webapp2.crm.repository.MessageRepository
import com.webapp2.crm.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime.now

@Service
class MessageServiceImpl(
    @Autowired
    private val messageRepository: MessageRepository,
    @Autowired
    private val contactRepository: ContactRepository
) : MessageService {
    @Transactional
    override fun getMessages(): List<MessageDto> {
        return messageRepository.findAll().map { it.toDto() }
    }

    @Transactional
    override fun getMessageHistory(messageId: Long): List<MessageStateDto> {
        return messageRepository.findById(messageId).orElse(null)
            .messageState!!
            .map { it.toDto() }
    }

    @Transactional
    override fun getMessageById(messageId: Long): MessageDto {
        return messageRepository.findById(messageId).orElse(null).toDto()
    }

    @Transactional
    override fun createMessage(messageDto: MessageDto): Boolean {
        try {
            val entity = Message()
            val sender = contactRepository.findById(messageDto.sender!!.id!!).orElse(null)
            val receivers = contactRepository.findAllById(messageDto.receivers!!.map { it.id }).toMutableSet()
            val messageState = MessageState()
            messageState.state = GeneralConstant.CREATED
            messageState.description = messageDto.state!!.description
            messageState.message = entity
            entity.sender = sender
            entity.receivers!!.addAll(receivers)
            entity.messageState = mutableSetOf(messageState)
            entity.interview = null
            entity.body = messageDto.body
            entity.date = messageDto.date
            receivers.forEach { it.receivedMessages.add(entity) }
            sender.sentMessages.add(entity)
            messageRepository.save(entity)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    @Transactional
    override fun changeMessageState(messageId: Long, messageStateDto: MessageStateDto): Boolean {
        try {
            val entity = messageRepository.findById(messageId).orElse(null)
            val messageState = MessageState()
            messageState.state = messageStateDto.state
            messageState.description = messageStateDto.description
            messageState.message = entity
            entity.messageState!!.add(messageState)
            messageRepository.save(entity)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}