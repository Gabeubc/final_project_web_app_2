package com.webapp2.crm.exception.contact

class AssignToException(
    private val msg: String = "contact's type association fail"
): Exception(msg) {
}