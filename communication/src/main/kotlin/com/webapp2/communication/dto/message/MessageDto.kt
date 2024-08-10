package com.webapp2.communication.dto.message

import com.webapp2.communication.dto.contact.ContactInformationDto
import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.entity.message.Message
import com.webapp2.communication.entity.utils.EntityBaseId

data class MessageDto (
    var receivers: MutableSet<ContactInformationDto?> = mutableSetOf(),
    var messageState: MutableSet<MessageStateDto> = mutableSetOf(),
    var sender: Contact,
    var content: String
): EntityBaseId<Long>(){
    fun toEntity(): Message {
        TODO()
    }
}