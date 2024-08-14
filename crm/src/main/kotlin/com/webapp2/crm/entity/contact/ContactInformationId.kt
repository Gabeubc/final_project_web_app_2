package com.webapp2.crm.entity.cotnact
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class ContactInformationId(
    var email: String = "",
    var phoneNumber: String = "",
    var address: String = ""
) : Serializable