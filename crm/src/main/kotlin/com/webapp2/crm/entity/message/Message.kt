package com.webapp2.crm.entity

import com.example.crm.entities.utils.EntityBaseId

data class Message (
    var name: String = "",
    var surname: String = "",
): EntityBaseId<Long>()