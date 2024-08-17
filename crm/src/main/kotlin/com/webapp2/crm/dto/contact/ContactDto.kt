package com.webapp2.crm.dto.contact

import com.webapp2.crm.dto.utils.EntityBaseId

data class Contact(
    var name: String = "",
    var surname: String = ""
) : EntityBaseId<Long>()
