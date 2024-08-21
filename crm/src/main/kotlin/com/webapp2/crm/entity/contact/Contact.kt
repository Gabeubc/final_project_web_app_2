package com.webapp2.crm.entity.contact

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.entity.message.Message
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "WA2_CONTACT")
data class Contact(
    var name: String = "",
    var surname: String = "",
    var birthDate: LocalDateTime? = null,
    @OneToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var customer: Customer? = null,
    @OneToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var professional: Professional? = null,
    @OneToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var operator: Operator? = null,
    @ManyToMany(
        fetch = FetchType.LAZY
    )
    var contactInformations: MutableSet<ContactInformation>? = mutableSetOf(),
    @OneToMany(
        mappedBy = "sender",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var sentMessages: MutableSet<Message> = mutableSetOf(),
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var receivedMessages: MutableSet<Message> = mutableSetOf()
) : EntityBaseId<Long>(){
    fun toDto(): ContactDto {
        var dto = ContactDto()
        dto.id = id
        dto.name = name
        dto.surname = surname
        if(customer!=null)
        dto.type = "customer"
        if(professional!=null)
            dto.type = "professional"
        if(operator!=null)
            dto.type = "operator"
        dto.informations = contactInformations!!.map { it.toDto() }
        return dto
    }
    fun toDto(id: Long?): ContactDto {
        var dto = ContactDto()
        dto.id = id
        dto.name = name
        dto.surname = surname
        if(customer!=null)
            dto.type = "customer"
        if(professional!=null)
            dto.type = "professional"
        if(operator!=null)
            dto.type = "operator"
        dto.informations = contactInformations!!.map { it.toDto() }
        return dto
    }

    override fun toString(): String {
        return ""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Contact) return false
        if (!super.equals(other)) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }

}
