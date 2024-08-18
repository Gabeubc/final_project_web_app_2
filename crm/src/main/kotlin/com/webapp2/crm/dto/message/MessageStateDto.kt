package com.webapp2.crm.dto.message

import com.webapp2.crm.dto.utils.EntityBaseId

data class MessageStateDto(
    var description: String = "",
    var state: String = ""
) : EntityBaseId<Long>()