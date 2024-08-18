package com.webapp2.crm.entity.contact

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class ContactInformationId(
    var email: String = ""
) : Serializable