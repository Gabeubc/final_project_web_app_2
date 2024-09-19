package com.webapp2.crm.service.message

import com.webapp2.crm.dto.message.MessageDto
import com.webapp2.crm.dto.message.MessageStateDto
import com.webapp2.crm.entity.message.Message
import com.webapp2.crm.entity.message.MessageState
import com.webapp2.crm.exception.contact.ContactNotFoundException
import com.webapp2.crm.exception.message.ChangeMessageStateException
import com.webapp2.crm.exception.message.MessageNotFoundException
import com.webapp2.crm.repository.ContactRepository
import com.webapp2.crm.repository.MessageRepository
import com.webapp2.crm.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
        return messageRepository.findById(messageId).orElseThrow{ MessageNotFoundException() }
            .messageState!!
            .map { it.toDto() }
    }

    @Transactional
    override fun getMessageById(messageId: Long): MessageDto {
        return messageRepository.findById(messageId).orElseThrow{ MessageNotFoundException() }.toDto()
    }

    @Transactional
    override fun createMessage(messageDto: MessageDto): Boolean {
        try {
            val entity = Message()
            val sender = contactRepository.findById(messageDto.sender!!.id!!).orElseThrow{ ContactNotFoundException() }
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
            throw MessageNotFoundException()
            return false
        }
    }

    @Transactional
    override fun changeMessageState(messageId: Long, messageStateDto: MessageStateDto): Boolean {
        try {
            val entity = messageRepository.findById(messageId).orElseThrow{MessageNotFoundException()}
            val messageState = MessageState()
            messageState.state = messageStateDto.state
            messageState.description = messageStateDto.description
            messageState.message = entity
            entity.messageState!!.add(messageState)
            messageRepository.save(entity)
            return true
        } catch (e: Exception) {
            throw ChangeMessageStateException()
            return false
        }
    }
}