package com.webapp2.communication.entity.contact

import com.webapp2.communication.dto.contact.ContactDto
import com.webapp2.communication.entity.message.Message
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity(name = "WA2_CONTACT")
data class Contact(
    @ManyToMany
    var contactInformations: MutableSet<ContactInformation> = mutableSetOf(),
    @ManyToMany
    var message: MutableSet<Message>,
    var name: String = "",
    var surname: String = "",
    var type: String = "",): EntityBaseId<Long>(){
    fun toDto(): ContactDto{
        TODO()
    }
}