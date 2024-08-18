package com.webapp2.crm.entity.message

import com.webapp2.crm.dto.message.MessageStateDto
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity(name="WA2_MESSAGE_STATE")
data class MessageState(
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var message: Message? = null,
    var description: String = "",
    var state: String = "",
    var date: LocalDateTime? = now()
) : EntityBaseId<Long>(){
    fun toDto() : MessageStateDto{
        val dto = MessageStateDto()
        dto.id = id
        dto.state = state
        dto.description = description
        return dto
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MessageState) return false
        if (!super.equals(other)) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}