package com.webapp2.communication.dto.message

import com.webapp2.communication.dto.contact.ContactInformationDto
import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.entity.message.Message
import com.webapp2.communication.entity.utils.EntityBaseId

data class MessageDto (
    var receiverIds: MutableSet<Long?> = mutableSetOf(),
    var messageStateId: MutableSet<Long> = mutableSetOf(),
    var senderId: Long,
    var content: String
): EntityBaseId<Long>(){
    fun toEntity(): Message {
        TODO()
    }
}