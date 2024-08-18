package com.webapp2.crm.entity.message

import com.webapp2.crm.dto.message.MessageDto
import com.webapp2.crm.entity.contact.Contact
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity(name = "WA2_MESSAGE")
data class Message(
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var sender: Contact? = null,
    @ManyToMany(
        mappedBy = "receivedMessages",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var receivers: MutableSet<Contact>? = mutableSetOf(),
    @OneToMany(
        mappedBy = "message",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var messageState: MutableSet<MessageState>? = mutableSetOf(),
    @OneToOne(
        mappedBy = "message",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var interview: Interview? = null,
    var body: String? = "",
    var date: LocalDateTime? = now()
) : EntityBaseId<Long>() {
    fun toDto(): MessageDto {
        val dto = MessageDto()
        dto.id = id
        if (sender != null)
            dto.sender = sender!!.toDto()
        if (receivers != null)
            dto.receivers = receivers!!.map { it.toDto() }
        dto.body = body
        dto.date = date
        if (messageState!!.isNotEmpty())
            dto.state = messageState!!.maxBy { it.date!! }.toDto()
        return dto
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Message) return false
        if (!super.equals(other)) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }
}