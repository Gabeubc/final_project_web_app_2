package com.webapp2.crm.dto.message

import com.webapp2.crm.dto.utils.EntityBaseId

data class MessageState(
    var message: Message? = null,
    var description: String = "",
    var state: String = ""
) : EntityBaseId<Long>()