package com.webapp2.communication.entity

import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.Entity

@Entity(name= "WA2_MAIL")
data class Mail(
    var sender: String = "",
    var receivers: List<String> = listOf(),
    var mailObject: String = "",
    var body: String = ""
): EntityBaseId<Long>()