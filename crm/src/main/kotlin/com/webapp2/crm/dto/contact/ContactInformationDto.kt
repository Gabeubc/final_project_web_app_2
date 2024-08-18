package com.webapp2.crm.dto.contact
import java.io.Serializable

data class ContactInformationDto(
    var email: String = "",
    var phoneNumber: String = "",
    var address: String = ""
) : Serializable