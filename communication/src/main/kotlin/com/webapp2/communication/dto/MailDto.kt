package com.webapp2.communication.dto

import com.webapp2.communication.dto.utils.EntityBaseId

data class MailDto(
    var sender: String = "",
    var receivers: List<String> = listOf(),
    var mailObject: String = "",
    var body: String = ""
): EntityBaseId<Long>()