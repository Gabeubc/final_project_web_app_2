package com.webapp2.crm.entity.contact

import com.webapp2.crm.dto.contact.ContactInformationDto
import jakarta.persistence.*

@Entity(name = "WA2_CONTACT_INFORMATION")
data class ContactInformation(
    @EmbeddedId
    var id: ContactInformationId,
    @ManyToMany(
        mappedBy = "contactInformations",
        fetch = FetchType.LAZY
    )
    var contacts: MutableSet<Contact> = mutableSetOf(),
    var phoneNumber: String = "",
    var address: String = ""
){
    fun toDto(): ContactInformationDto {
        val dto = ContactInformationDto()
        dto.email = id.email
        dto.phoneNumber = phoneNumber
        dto.address = address
        return dto
    }

    override fun toString(): String {
        return "ContactInformation()"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ContactInformation) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
