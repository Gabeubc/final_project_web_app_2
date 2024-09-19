package com.webapp2.crm.exception.contact

class ContactCreationException(
    private val msg: String = "contact creation fail"
): Exception(msg) {
}