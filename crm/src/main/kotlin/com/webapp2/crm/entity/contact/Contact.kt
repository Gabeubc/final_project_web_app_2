package com.webapp2.crm.entity.cotnact

import com.example.crm.entities.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany

@Entity(name = "WA2_CONTACT")
data class Contact(
    var name: String = "",
    var surname: String = "",
    @ManyToMany(
        mappedBy = "contacts",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var contactInformations: MutableSet<ContactInformation>? = null
) : EntityBaseId<Long>()
