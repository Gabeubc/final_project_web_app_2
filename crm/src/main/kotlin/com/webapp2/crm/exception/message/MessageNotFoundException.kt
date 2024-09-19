package com.webapp2.crm.exception.message

class MessageNotFoundException(
    private val msg: String = "request message not found"
): Exception(msg) {
}