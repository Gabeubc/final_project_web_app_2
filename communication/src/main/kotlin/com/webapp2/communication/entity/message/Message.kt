package com.webapp2.communication.entity.message

import com.webapp2.communication.dto.message.MessageDto
import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.entity.contact.ContactInformation
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany

@Entity(name = "WA2_MESSAGE")
data class Message(
    @ManyToMany(
        mappedBy = "message",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var receivers: MutableSet<ContactInformation?> = mutableSetOf(),
    @OneToMany(
        mappedBy = "message",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var messageState: MutableSet<MessageState> = mutableSetOf(),
    var sender: Contact,
    var content: String
): EntityBaseId<Long>(){
    fun toDto(): MessageDto{
        TODO()
    }
}
