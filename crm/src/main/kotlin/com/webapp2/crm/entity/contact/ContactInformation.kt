package com.webapp2.crm.entity.cotnact

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity(name = "WA2_CONTACT_INFORMATION")
data class ContactInformation(
    @EmbeddedId
    var id: ContactInformationId,
    @ManyToMany
    var contacts: MutableSet<Contact> = mutableSetOf()
)
