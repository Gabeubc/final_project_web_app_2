package com.webapp2.communication.dto.message

import com.webapp2.communication.entity.utils.EntityBaseId

data class MessageStateDto (
    var messageId: Long,
    var state: String
): EntityBaseId<Long>()