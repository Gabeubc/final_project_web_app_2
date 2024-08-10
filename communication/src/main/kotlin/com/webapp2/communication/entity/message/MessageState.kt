package com.webapp2.communication.entity.message

import com.webapp2.communication.dto.message.MessageStateDto
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany

@Entity(name = "WA2_MESSAGE_STATE")
data class MessageState(
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var message: Message,
    var state: String
):EntityBaseId<Long>(){
    fun toDto(): MessageStateDto {
        TODO()
    }
}
