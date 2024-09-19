package com.webapp2.crm.exception.message

class CreateMessageException(
    private val msg: String = "message creation fail"
): Exception(msg) {
}